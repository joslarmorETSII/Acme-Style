package services;

import domain.*;
import domain.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.PanelRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class PanelService {

    // Managed repository -----------------------------------------------------

    @Autowired
    private PanelRepository panelRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;



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

    public Collection<Panel> findAll(){
        Collection<Panel> res;
        res= panelRepository.findAll();
        return  res;
    }

    public Panel findOne(int panelId){
        return panelRepository.findOne(panelId);
    }

    public Panel findOneToEdit(final int id) {
        Panel res;

        res= this.panelRepository.findOne(id);
        Assert.isTrue(res.getUser().equals(userService.findByPrincipal()));
        return  res;
    }


    public Panel save(Panel panel){
        Assert.notNull(panel);
        User principal = this.userService.findByPrincipal();
        Panel result;

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


    public Panel reconstructS(final Panel panelPruned, final BindingResult binding) {
        Panel res;
        if (panelPruned.getId() == 0) {
            res = this.create();
        } else {
            res = this.findOne(panelPruned.getId());

        }
        res.setName(panelPruned.getName());

        this.validator.validate(res,binding);

        return res;


    }


}
