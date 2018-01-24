package test.dictionary.ui;


import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.dictionary.model.Dictionary;
import test.dictionary.network.DictionaryApi;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivity activity;
    private DictionaryApi dictionaryApi;

    public MainActivityPresenter(MainActivity activity, DictionaryApi dictionary) {
        this.activity = activity;
        this.dictionaryApi = dictionary;

    }

    @Override
    public void doSearch(String term) {
        activity.showLoading();
        dictionaryApi.getAllItems(term)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::onError)
                .subscribe(this::onSuccess, this::onError);
    }


    private void onError(Throwable throwable) {
        activity.hideLoading();
        Log.e("t", throwable.getLocalizedMessage());
    }

    private void onSuccess(Dictionary dictionaryItems) {
        activity.hideLoading();
        activity.populateItems(dictionaryItems);
        Log.e("Suc", "suc");
    }
}
