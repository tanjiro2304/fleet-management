package com.vn.fleetmanagement.modules.datagrid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.fleetmanagement.models.BusVO;
import com.vn.fleetmanagement.mvputils.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UIScope
@SpringComponent
@Route("/grid")
public class DataGridView extends BaseView<DataGridPresenter> {

    private Button addButton;

    @Autowired
    private AddDialog addDialog;

    @Autowired
    private UpdateDialog updateDialog;

    private Grid<BusVO> busVOGrid;
    @Override
    protected void init() {
        setGrid();
        setSizeFull();
        setButton();
        add(addButton,busVOGrid);
    }

    protected void setGrid(){
        busVOGrid = new Grid<>();
        busVOGrid.addColumn(BusVO::getBusId).setHeader("Bus Id");
        busVOGrid.addColumn(BusVO::getModel).setHeader("Bus Model");
        busVOGrid.addColumn(busVO -> busVO.getRouteVO().getRouteNo()).setHeader("Route");
        busVOGrid.addColumn(busVO -> busVO.getDepotVO().getDepotName()).setHeader("Depot");
        busVOGrid.setItems(getPresenter().getData());
        busVOGrid.setSizeFull();

        busVOGrid.addItemClickListener(event-> {
           updateDialog.setBusVOBinder(event.getItem());
           updateDialog.open();
        });
    }

    protected void setButton(){
        addButton = new Button("ADD");
        addButton.addClickListener(buttonClickEvent -> addDialog.open());
    }
}
