package com.antho.newsreader.view.fragments.mostpopular;
/** A simple {@link Fragment} subclass. **/
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antho.newsreader.R;
import com.antho.newsreader.model.mostpopular.MostPopularNewsList;
import com.antho.newsreader.viewmodel.MostPopularViewModel;
/** Handle the most popular stories fragment for viewpager **/
public class MostPopular extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;
    // Required empty public constructor
    public MostPopular()
    {
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MostPopular.
     */
    // TODO: Rename and change types and number of parameters
    public static MostPopular newInstance(String param1, String param2) {
        MostPopular fragment = new MostPopular();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    // Inflate the layout for this fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_most_popular, container, false);
    }
    //
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_most_popular);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        MostPopularViewModel model = ViewModelProviders.of(this).get(MostPopularViewModel.class);
        model.getNews().observe(this, new Observer<MostPopularNewsList>()
        {
            @Override
            public void onChanged(@Nullable MostPopularNewsList newsList)
            {
                MostPopularAdapter adapter = new MostPopularAdapter(getContext(), newsList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    //
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    //
    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}