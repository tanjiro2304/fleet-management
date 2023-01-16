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

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@UIScope
@Scope(value = "prototype")
@SpringComponent
public class UpdateDialog extends Dialog {
    private TextField busId, model, type, regNo;
    private ComboBox<DepotVO> depotVOComboBox;
    private ComboBox<RouteVO> routeVOComboBox;
    private Button updateButton;

    private Button cancelButton;

    private VerticalLayout layout;

    private Binder<BusVO> busVOBinder;

    @Autowired
    DepotServiceWrapper depotServiceWrapper;

    @Autowired
    RouteServiceWrapper routeServiceWrapper;

    @Autowired
    BusServiceWrapper busServiceWrapper;

    @PostConstruct
    public void init(){
        setLayout();
    }

    public void setLayout(){
        busId = new TextField("Bus Id");
        model = new TextField("Model");
        type = new TextField("Type");
        regNo = new TextField("Registration No");
        depotVOComboBox = new ComboBox<>("Select a Depot");
        depotVOComboBox.setItemLabelGenerator(DepotVO::getDepotName);
        depotVOComboBox.setItems(depotServiceWrapper.getAllDepot());
        routeVOComboBox = new ComboBox<>("Select a Route");
        routeVOComboBox.setItemLabelGenerator(RouteVO::getRouteNo);
        routeVOComboBox.setItems(routeServiceWrapper.getRouteVOs());

        updateButton = new Button("Update");
        updateButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);
        cancelButton = new Button("Cancel");
        cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        layout = new VerticalLayout();
        HorizontalLayout buttonLayout = new HorizontalLayout(updateButton,cancelButton);
        layout.add(busId,model,type,regNo,depotVOComboBox,routeVOComboBox,buttonLayout);

        add(layout);
    }

    public void setBusVOBinder(BusVO busVO) {
        busVOBinder = new Binder<>();
        busVOBinder.setBean(busVO);
        busVOBinder.forField(busId).withNullRepresentation("").
                withConverter(new StringToLongConverter("Not a Number")).bind(BusVO::getBusId,BusVO::setBusId);

        busVOBinder.forField(model).withNullRepresentation("").
                bind(BusVO::getModel,BusVO::setModel);

        busVOBinder.forField(regNo).withNullRepresentation("").bind(BusVO::getRegistrationNo, BusVO::setRegistrationNo);

        busVOBinder.forField(type).withNullRepresentation("").
                bind(BusVO::getType, BusVO::setType);

        busVOBinder.forField(depotVOComboBox).
                bind(BusVO::getDepotVO,BusVO::setDepotVO);

        busVOBinder.forField(routeVOComboBox).bind(BusVO::getRouteVO,BusVO::setRouteVO );

        updateButton.addClickListener(buttonClickEvent ->

        {

            busServiceWrapper.saveToDB(DTOConvertUtils.
                    convertVOtoDTO(busVOBinder.getBean()));
        });
    }

}
