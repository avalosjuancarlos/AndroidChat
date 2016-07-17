package edu.galileo.android.androidchat.contactlist;

/**
 * Created by avalo.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    private ContactListRepository repository;

    public ContactListSessionInteractorImpl(ContactListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
