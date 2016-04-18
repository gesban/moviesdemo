package com.jonathangama.view;

/**
 * Created by Jonathan Gama on 4/11/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonathangama.dao.GlobalVariables;
import com.jonathangama.R;

public class BookFragment extends UpdatableFragment implements View.OnClickListener {

    Context context;
    Communicator communicator;
    public TextView txtBookAuthor;
    public TextView txtBookTitle;
    public ImageView imgBookImage;

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        //communicator exist in case need to interface with the activity
        communicator = (Communicator) a;
    }


    @Override
    public void updateUI(String json)
    {
        //not required by the moment but UpdatebleFragment forces its use
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_view_fragment, container, false);

        context = getActivity().getApplicationContext();

        imgBookImage = (ImageView) v.findViewById(R.id.book_image);
        txtBookAuthor = (TextView) v.findViewById(R.id.book_author);
        txtBookTitle = (TextView) v.findViewById(R.id.book_title);

        GlobalVariables.getInstance().setActiveFragmentIndex(1);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtBookAuthor.setText(GlobalVariables.getInstance().getBook().getBookAuthor());
        txtBookTitle.setText(GlobalVariables.getInstance().getBook().getBookTitle());
        imgBookImage.setImageBitmap(GlobalVariables.getInstance().getBook().getBookImage());

    }


    @Override
    public void onClick(View v) {
       // write here in case we need to perform some actions
    }



}


