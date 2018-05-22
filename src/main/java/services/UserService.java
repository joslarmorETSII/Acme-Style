package services;

import domain.*;
import forms.SubscribeServiseForm;
import forms.UserForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class UserService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private UserRepository userRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ProfileService profileService;


    // Constructors -----------------------------------------------------------

    public UserService() { super(); }

    // Simple CRUD methods ----------------------------------------------------

    public User create() {
        User result;

        result = new User();
        result.setFollowers(new ArrayList<Actor>());
        result.setFollowings(new ArrayList<Actor>());
        result.setFolders(new ArrayList<Folder>());
        result.setParticipates(new ArrayList<Participate>());
        result.setFeedbacks(new ArrayList<Feedback>());
        result.setPanels(new ArrayList<Panel>());
        result.setRaffles(new ArrayList<Raffle>());
        result.setSubscriptions(new ArrayList<Subscription>());
        result.setComments(new ArrayList<Comment>());
        result.setPosts(new ArrayList<Post>());
        result.setUserAccount(this.userAccountService.create("USER"));

        return result;
    }

    public User findOne(final int userId) {

        User result;
        result = this.userRepository.findOne(userId);
        return result;
    }

    public Collection<User> findAll() {

        Collection<User> result;
        result = this.userRepository.findAll();
        return result;
    }

    public User save(User user) {
        Assert.notNull(user);
        User result;
        Collection<Folder> folders;

        if(user.getId()==0){
            result = userRepository.save(user);
            folders = actorService.generateFolders(result);
            Profile saved = profileService.generateProfileForActors(result);
            result.setProfile(saved);
            result.setFolders(folders);
            result = userRepository.save(result);
        }else {
            result = this.userRepository.save(user);
        }
        return result;
    }

    // Other business methods -----------------------------------------------------------------

    public User findByPrincipal() {

        User result;
        UserAccount userAccount = LoginService.getPrincipal();
        result = this.findByUserAccountId(userAccount.getId());
        return result;
    }


    public User findByUserAccountId(int userAccountId) {

        User result;
        result = this.userRepository.findByUserAccountId(userAccountId);
        return result;
    }

    public User reconstruct(UserForm userForm, BindingResult binding) {

        User result;

        result = this.create();
        result.getUserAccount().setUsername(userForm.getUsername());
        result.setName(userForm.getName());
        result.setSurname(userForm.getSurname());
        result.setPhone(userForm.getPhone());
        result.setEmail(userForm.getEmail());
        result.setPostalAddresses(userForm.getPostalAddresses());
        result.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword(userForm.getPassword(), null));

        actorService.comprobarContrasena(userForm.getPassword(), userForm.getRepeatPassword(), binding);

        return result;
    }



    public Collection<Actor> follow(int actorId){
        Assert.notNull(actorId);
        Actor principal;
        Actor actorToFollow;

        principal = actorService.findByPrincipal();
        actorToFollow = actorService.findOne(actorId);
        Assert.isTrue(!principal.getFollowings().contains(actorToFollow));

        principal.getFollowings().add(actorToFollow);

        Assert.isTrue(principal.getId() != actorId);

        actorService.save(principal);
        actorService.save(actorToFollow);

        return principal.getFollowings();
    }

    public Collection<Actor> unfollow(int actorId){
        Assert.notNull(actorId);
        Actor principal;
        Actor actorToUnFollow;

        principal = findByPrincipal();
        actorToUnFollow = userRepository.findOne(actorId);
        Assert.isTrue(principal.getFollowings().contains(actorToUnFollow));
        principal.getFollowings().remove(actorToUnFollow);
        actorToUnFollow.getFollowers().remove(principal);

        Assert.isTrue(principal.getId() != actorId);

        actorService.save(principal);
        actorService.save(actorToUnFollow);

        return principal.getFollowings();
    }

    public void flush() {
        userRepository.flush();
    }

    public CreditCard reconstructSubscribeServise(SubscribeServiseForm subscribeVolumeForm, BindingResult binding) {
        CreditCard creditCard = new CreditCard();

        creditCard.setBrand(subscribeVolumeForm.getBrand());
        creditCard.setCvv(subscribeVolumeForm.getCvv());
        creditCard.setExpirationMonth(subscribeVolumeForm.getExpirationMonth());
        creditCard.setExpirationYear(subscribeVolumeForm.getExpirationYear());
        creditCard.setHolder(subscribeVolumeForm.getHolder());
        creditCard.setNumber(subscribeVolumeForm.getNumber());

        checkMonth(subscribeVolumeForm.getExpirationMonth(),subscribeVolumeForm.getExpirationYear(),binding);
        return creditCard;
    }

    private boolean checkMonth(Integer month, Integer year, BindingResult binding) {
        FieldError error;
        String[] codigos;
        boolean result;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Integer actualMonth = c.get(Calendar.MONTH)+1;
        Integer actualYear = c.get(Calendar.YEAR);

        if (month!=null && year!=null)
            result = actualYear.equals(year) && month<actualMonth;
        else
            result = false;
        if (result) {
            codigos = new String[1];
            codigos[0] = "creditCard.month.invalid";
            error = new FieldError("registerAdvertisementForm", "expirationMonth", month, false, codigos, null, "should not be in the past");
            binding.addError(error);
        }
        return result;
    }
}
