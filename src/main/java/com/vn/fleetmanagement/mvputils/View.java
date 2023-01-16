package com.vn.fleetmanagement.mvputils;

public interface View<P extends Presenter> {
    P getPresenter();
}
