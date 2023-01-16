package com.vn.fleetmanagement.modules.datagrid;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.fleetmanagement.converter.DTOConvertUtils;
import com.vn.fleetmanagement.models.BusVO;
import com.vn.fleetmanagement.models.DepotVO;
import com.vn.fleetmanagement.models.RouteVO;
import com.vn.fleetmanagement.servicewrapper.BusServiceWrapper;
import com.vn.fleetmanagement.servicewrapper.DepotServiceWrapper;
import com.vn.fleetmanagement.servicewrapper.RouteServiceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.gatanaso.MultiselectComboBox;

import javax.annotation.PostConstruct;


@UIScope
@Scope(value = "prototype")
@SpringComponent
public class AddDialog extends Dialog {
    private TextField busId, model, type, regNo;



    private ComboBox<DepotVO> depotVOComboBox;

    private Binder<BusVO> busVOBinder;

    private VerticalLayout layout;

    private Button saveButton;

    private Button cancelButton;

    private MultiselectComboBox<RouteVO> routeVOMultiselectComboBox;

    @Autowired
    private BusServiceWrapper busServiceWrapper;

    @Autowired
    private DepotServiceWrapper depotServiceWrapper;

    @Autowired
    private RouteServiceWrapper routeServiceWrapper;


    @PostConstruct
    private void init(){
        setLayout();
    }


    private void setLayout(){
        busId = new TextField("Bus Id");
        model = new TextField("Model");
        type = new TextField("Type");
        regNo = new TextField("Registration No");
        depotVOComboBox = new ComboBox<>("Depot");
        depotVOComboBox.setItemLabelGenerator(DepotVO::getDepotName);
        depotVOComboBox.setItems(depotServiceWrapper.getAllDepot());

        routeVOMultiselectComboBox = new MultiselectComboBox<>("Select a Route");
        routeVOMultiselectComboBox.setItemLabelGenerator(RouteVO::getRouteNo);
        routeVOMultiselectComboBox.setItems(routeServiceWrapper.getRouteVOs());

        saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);
        cancelButton = new Button("Cancel");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        layout = new VerticalLayout();
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton,cancelButton);
        layout.add(busId,model,type,regNo,depotVOComboBox,routeVOMultiselectComboBox,buttonLayout);
        setDepotVOBinder();
        add(layout);
    }

    private void setDepotVOBinder(){
        busVOBinder = new Binder<>();
        busVOBinder.setBean(new BusVO());
        busVOBinder.forField(busId).withNullRepresentation("").

                withConverter(new StringToLongConverter("Not a Number")).bind(BusVO::getBusId,BusVO::setBusId);

        busVOBinder.forField(model).withNullRepresentation("").

                bind(BusVO::getModel,BusVO::setModel);

        busVOBinder.forField(regNo).withNullRepresentation("").bind(BusVO::getRegistrationNo,BusVO::setRegistrationNo);

        busVOBinder.forField(type).withNullRepresentation("").

                bind(BusVO::getType, BusVO::setType);

        busVOBinder.forField(depotVOComboBox).
                bind(BusVO::getDepotVO,BusVO::setDepotVO);

        busVOBinder.forField(routeVOMultiselectComboBox).bind(BusVO::getRouteVOSet,BusVO::setRouteVOSet);

        saveButton.addClickListener(buttonClickEvent -> {
            BusVO busVO = busVOBinder.getBean();
            busServiceWrapper.
                    saveToDB(DTOConvertUtils.convertVOtoDTO(busVOBinder.getBean()));
            close();
        });

        cancelButton.addClickListener(buttonClickEvent -> close());
    }
}
