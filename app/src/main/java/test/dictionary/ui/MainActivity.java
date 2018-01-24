package test.dictionary.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import test.dictionary.MyApp;
import test.dictionary.R;
import test.dictionary.model.Dictionary;
import test.dictionary.model.DictionaryResultItems;
import test.dictionary.network.DictionaryApi;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    DictionaryApi dictionary;

    private ProgressDialog pDialog;
    MainActivityContract.Presenter presenter;
    private RecyclerView recyclerView;
    private DictionaryItemsAdapter adapter;
    private List<DictionaryResultItems> resultItems;

    private EditText etSearchText;
    private ImageButton btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        ((MyApp)getApplication()).getMyComponent(this).injectMe(this);
        setPresenter();

    }

    private void bindViews() {
        etSearchText = findViewById(R.id.search_tem);
        recyclerView = findViewById(R.id.recycler_view);

        resultItems = new ArrayList<>();
        adapter = new DictionaryItemsAdapter(this, resultItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }

    private void setPresenter() {
        presenter = new MainActivityPresenter(this,dictionary);
    }

    @Override public void showLoading() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Searching...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override public void hideLoading() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void populateItems(Dictionary dictionary) {
        if(dictionary != null && dictionary.getList() != null) {
            resultItems = dictionary.getList();
            adapter.updateList(resultItems);
        }
    }

    public void doSearch(View view) {
        String searchedText = etSearchText.getText().toString();
        if(!TextUtils.isEmpty(searchedText)) {
            presenter.doSearch(searchedText);
        }
    }
}
