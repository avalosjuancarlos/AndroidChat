package edu.galileo.android.androidchat.addcontact;

/**
 * Created by avalo.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepository repository;

    public AddContactInteractorImpl(AddContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
