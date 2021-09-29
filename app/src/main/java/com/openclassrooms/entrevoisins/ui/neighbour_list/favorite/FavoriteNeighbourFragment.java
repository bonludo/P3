package com.openclassrooms.entrevoisins.ui.neighbour_list.favorite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.OnNeighbourClickedListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteNeighbourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteNeighbourFragment extends Fragment {

    private NeighbourRepository mNeighbourRepository;
    private List<Neighbour> mNeighboursFavorite;
    private OnNeighbourClickedListener onNeighbourClickedListener;

    private RecyclerView mRecyclerView;


    // TODO: Rename and change types and number of parameters
    public static FavoriteNeighbourFragment newInstance() {
        return new FavoriteNeighbourFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onNeighbourClickedListener = (OnNeighbourClickedListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNeighbourRepository = DI.getNeighbourRepository();
        mNeighboursFavorite = mNeighbourRepository.getFavorites();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    private void initList() {
        mNeighboursFavorite = mNeighbourRepository.getFavorites();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighboursFavorite, onNeighbourClickedListener));
        //3 intermédiaire qui met en contact l'activity et l'adapteur a l'aide de onNeighbourclickedListener
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mNeighbourRepository.deleteNeighbour(event.neighbour);
        initList();
    }


}