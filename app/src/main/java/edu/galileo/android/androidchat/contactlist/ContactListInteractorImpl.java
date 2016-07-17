package edu.galileo.android.androidchat.contactlist;

/**
 * Created by avalo.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    private ContactListRepository repository;

    public ContactListInteractorImpl(ContactListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToContactListEvents();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
