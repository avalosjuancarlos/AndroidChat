package edu.galileo.android.androidchat.addcontact;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.androidchat.addcontact.events.AddContactEvent;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;
import edu.galileo.android.androidchat.lib.EventBus;

/**
 * Created by avalo.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view, EventBus eventBus, AddContactInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if(view != null){
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if(view != null){
            view.hideProgress();
            view.showInput();

            if(event.isError()){
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
