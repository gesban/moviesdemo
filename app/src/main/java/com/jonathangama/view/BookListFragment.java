package com.jonathangama.view;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.jonathangama.dao.GlobalVariables;
import com.jonathangama.R;
import com.jonathangama.dao.JSONSource;
import com.jonathangama.dao.WebServiceConnector;
import com.jonathangama.model.Book;


public class BookListFragment extends UpdatableFragment implements OnStartDragListener {

    JSONSource jsonSource;
    Communicator communicator;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    BookArrayAdapter bookArrayAdapter;
    String json="";
    Context context;
    private ItemTouchHelper mItemTouchHelper;



    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        communicator = (Communicator) a;
    }

    private void initSwipe(BookArrayAdapter bookArrayAdapter,RecyclerView recyclerView)
    {
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(bookArrayAdapter){

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();

                Toast.makeText(context, "Selected=" + position, Toast.LENGTH_SHORT).show();

                if (super.getDireccion()==1)
                {
                    Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();

                    try {
                       Book book = GlobalVariables.getInstance().getBooks().get(position);
                        Toast.makeText(context, book.getBookTitle(), Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                if (super.getDireccion()==2)
                {
                    Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();

                }
                super.onSwiped(viewHolder, direction);


            }

        };
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }


   @Override
    public void updateUI(String json)
    {
        jsonSource = new JSONSource();
        if (json.trim().length()>0) {
            GlobalVariables.getInstance().setBooks(jsonSource.getBooksFromJSON(json));
            reloadAdapter();
        }
    }

    public void reloadAdapter()
    {
        bookArrayAdapter = new BookArrayAdapter(getActivity(),communicator,GlobalVariables.getInstance().getBooks(), this);
        recyclerView.setAdapter(bookArrayAdapter);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        initSwipe(bookArrayAdapter, recyclerView);

        progressDialog.dismiss();

    }




//returns View  return v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_list_fragment, container, false);
        recyclerView  = (RecyclerView) v.findViewById(R.id.list);
        context = getActivity().getApplicationContext();

        return v;

    }


    private void runQuery(String query)
    {
        String url = query.replace(" ", "%20");

        connect(url);
    }



    WebServiceConnector webServiceConnector;
    public void connect(String fullUrl)
    {
        webServiceConnector = new WebServiceConnector(this, json);
        try {
            webServiceConnector.execute(fullUrl);
        } catch (Exception e) {
            webServiceConnector.cancel(true);
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();

        if(webServiceConnector!=null) {
            webServiceConnector.cancel(false);
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();

    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }


    ProgressDialog progressDialog;
   @Override
    public void reloadData()
   {
       showProgresDialog("Loading...");
       runQuery(GlobalVariables.getInstance().getWebServiceURL());
   }

    private void showProgresDialog(String message)
    {
        progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void loadData()
    {
        showProgresDialog("Loading...");

        if (!(GlobalVariables.getInstance().getBooks()!=null&&GlobalVariables.getInstance().getBooks().size()>=0))
        {
            runQuery(GlobalVariables.getInstance().getWebServiceURL());
            return;
        }
        else
        {
            reloadAdapter();
        }
    }


}


