package com.jonathangama.view;
/**
 * Created by Jonathan Gama on 4/11/2016.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jonathangama.dao.GlobalVariables;
import com.jonathangama.R;
import com.jonathangama.dao.DownloadImageTask;
import com.jonathangama.model.Book;

import java.util.ArrayList;
import java.util.Collections;


public class BookArrayAdapter extends RecyclerView.Adapter<BookArrayAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private ArrayList<Book> books;
    private final OnStartDragListener startDragListener;
    Context context;
    Communicator communicator;
    int selectedItem=0;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView txtBookAuthor;
        public TextView txtBookTitle;
        public ImageView txtBookImage;
        public ViewHolder(View v) {
            super(v);

            txtBookImage = (ImageView) v.findViewById(R.id.book_image);
            txtBookAuthor = (TextView) v.findViewById(R.id.book_author);
            txtBookTitle = (TextView) v.findViewById(R.id.book_title);

            v.setOnClickListener(this);
        }


        private void unselect()
        {
            for (Book book : books) {
                book.setBookSelected(0);
            }
        }

        @Override
        public void onClick(View v) {
            selectedItem = getLayoutPosition();
            Book book = GlobalVariables.getInstance().getBooks().get(selectedItem);
            GlobalVariables.getInstance().setBook(book);

            toast("Selected record number " + selectedItem + ", title=" + book.getBookTitle() +  "and author=" + book.getBookAuthor());

            communicator.showBook(book);

        }
    }




    public void add(int position, String item) {
      //fix later  persons.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Book item) {
        int position = books.indexOf(item);
        books.remove(position);
        notifyItemRemoved(position);
    }


    public BookArrayAdapter(Context context, Communicator communicator,ArrayList<Book> books, OnStartDragListener startDragListener) {
        this.books = books;
        this.startDragListener = startDragListener;
        this.context=context;
        this.communicator=communicator;
    }
    @Override
    public BookArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_row, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(final BookArrayAdapter.ViewHolder holder, int position) {
         // get element from your dataset at this position

        final Book book = books.get(position);

        holder.txtBookTitle.setText(books.get(position).getBookTitle());
        holder.txtBookAuthor.setText(books.get(position).getBookAuthor());

        if (book.getBookImage()==null) {
            DownloadImageTask downloadImageTask;
            downloadImageTask = new DownloadImageTask(context, books.get(position), holder.txtBookImage);
            downloadImageTask.execute(books.get(position).getBookUrl());
        }
        else
        {
            holder.txtBookImage.setImageBitmap(book.getBookImage());
        }


        holder.txtBookImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startDragListener.onStartDrag(holder);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    @Override
    public void onItemDismiss(int position) {
        books.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(books, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    private void toast(String message)
    {
        if (GlobalVariables.getInstance().getDebug()==1)
        {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

}