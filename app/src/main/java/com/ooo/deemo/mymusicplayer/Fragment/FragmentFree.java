package com.ooo.deemo.mymusicplayer.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.ooo.deemo.mymusicplayer.MusicListAdapter;
import com.ooo.deemo.mymusicplayer.R;
import com.ooo.deemo.mymusicplayer.Song;
import com.ooo.deemo.mymusicplayer.Utils.MUtils;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentFree.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentFree#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFree extends Fragment implements View.OnClickListener {

    private static boolean mBackKeyPressed = false;

    private RecyclerView rv_log ;

    private MusicListAdapter rAdapter;

    private static List<Song> tl_List;

    private List<Song> searchList = new ArrayList<>();


    private Button pre_bt ;

    public static Button play_bt;

    private Button next_bt;

    private EditText search_edit;

    private ImageButton search_bt;

    private ImageButton flow_bt;


    SQLiteDatabase db = LitePal.getDatabase();





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentFree() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFree.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFree newInstance(String param1, String param2) {
        FragmentFree fragment = new FragmentFree();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_free, container, false);
        tl_List = new ArrayList<>();
        tl_List = MUtils.getmusic(getContext());
        rv_log =view.findViewById(R.id.ff_rv_log);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_log.setLayoutManager(layoutManager);
        rv_log.setFocusableInTouchMode(true);
        rAdapter = new MusicListAdapter(tl_List);
        rv_log.setAdapter(rAdapter);




        search_edit = view.findViewById(R.id.ff_search_edit);

        search_bt = view.findViewById(R.id.ff_search_bt);

        play_bt = view.findViewById(R.id.ff_play_bt);

        pre_bt = view.findViewById(R.id.ff_pre_bt);

        next_bt = view.findViewById(R.id.ff_next_bt);

        flow_bt = view.findViewById(R.id.ff_flow_bt);



        play_bt.setOnClickListener(this);

        pre_bt.setOnClickListener(this);

        next_bt.setOnClickListener(this);

        search_bt.setOnClickListener(this);

        search_edit.setOnClickListener(this);

        flow_bt.setOnClickListener(this);





        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0) {


                    searchMusic(s.toString());
                    Log.e("", "afterTextChanged:" + s.toString());

                    rAdapter = new MusicListAdapter(searchList);
                    rv_log.setAdapter(rAdapter);
                    rAdapter.notifyDataSetChanged();
                }else {
                    search_edit.setCursorVisible(false);


                    rAdapter = new MusicListAdapter(tl_List);
                    rv_log.setAdapter(rAdapter);
                    rAdapter.notifyDataSetChanged();

                }

            }
        });




        return view;
    }



    private void searchMusic(String s){
        int i = 0;
        i = s.length();
        String sLength = "";
        searchList.clear();
List<Song> songs = LitePal.findAll(Song.class);

for (Song songitem: songs){
    if(songitem.getSong().length()>i){
        sLength = songitem.getSong().substring(1,i+1);

        if (s.toUpperCase().equals(sLength.toUpperCase())) {

            searchList.add(songitem);

        }
    }
    if(songitem.getSinger().length()>i){
        sLength = songitem.getSinger().substring(0,i);
        if (s.toUpperCase().equals(sLength.toUpperCase())){
            searchList.add(songitem);
        }
    }


}



    }









    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /*
    点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ff_play_bt:



                break;
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
