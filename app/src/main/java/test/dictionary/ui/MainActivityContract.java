package test.dictionary.ui;

import test.dictionary.model.Dictionary;

/**
 * Created by imran on 23/01/18.
 */

public interface MainActivityContract {
    interface Presenter {
        void doSearch(String term);
    }

    interface View {
        void showLoading();
        void hideLoading();

        void populateItems(Dictionary dictionary);
    }
}
