package services;

import domain.Panel;
import domain.Photo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PanelRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class PanelService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private PanelRepository panelRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserService userService;

    // Constructors -----------------------------------------------------------

    public PanelService() {
        super();
    }

    // CRUD methods -----------------------------------------------------------

    public Panel create(){
        Panel result;
        User principal;

        principal = userService.findByPrincipal();
        result = new Panel();
        result.setPhotos(new ArrayList<Photo>());
        result.setUser(principal);

        return result;
    }

    public Panel findOne(int panelId){
        return panelRepository.findOne(panelId);
    }

    public Panel save(Panel panel){
        Assert.notNull(panel);
        User principal;
        Panel result;

        principal = userService.findByPrincipal();
        Assert.isTrue(panel.getUser().equals(principal));
        if(panel.getId()==0){
            result = panelRepository.save(panel);
            principal.getPanels().add(result);
            userService.save(principal);
        }else{
            result = panelRepository.save(panel);
        }
        return result;
    }

    public void delete(Panel panel){
        Assert.isTrue(panel.getUser().equals(userService.findByPrincipal()));
        panelRepository.delete(panel);
    }

    // Other business methods -------------------------------------------------



}
