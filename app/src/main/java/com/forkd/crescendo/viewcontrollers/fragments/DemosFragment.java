package com.forkd.crescendo.viewcontrollers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.adapters.DemosAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemosFragment extends Fragment {

   private List<Artwork> demos;
    private RecyclerView demosRecyclerView;
    private RecyclerView.LayoutManager demosLayoutManager;
    private DemosAdapter demosAdapter;

    private String JWT;

    private boolean MOCK_DATA = true;
    private ArrayList<Artwork> DEMOS_MOCK = new ArrayList<Artwork>();

    public DemosFragment() {
        DEMOS_MOCK.add(new Artwork("PIOXABoz-34", "BEST DESPACITO covers in The Voice | The Voice Global", "The single \"Despacito\" has made history as the most frequently streamed song of all time. Therefore, we created a compilation with some of the best Despacito ...", "https://i.ytimg.com/vi/PIOXABoz-34/default.jpg"));
        DEMOS_MOCK.add(new Artwork("jWb4T5R6Z_E", "Pink Watches Fan Covers On YouTube | Glamour", "On this episode of \"You Sang My Song,\" Pink watches YouTube fan covers of her songs \"What About Us,\" \"Perfect,\" \"Try,\" \"Who Knew,\" \"Just Give Me a Reason,\" ...", "https://i.ytimg.com/vi/jWb4T5R6Z_E/default.jpg"));
        DEMOS_MOCK.add(new Artwork("Cv7Q3a_2lYI", "rockstar (Post Malone) - Sofia Karlberg Cover", "Don't forget to follow me on Instagram, Facebook & Snapchat   Instagram: http://instagram.com/sofiakarlberg Facebook: http://facebook.com/sofiakarlbergofficial ...", "https://i.ytimg.com/vi/Cv7Q3a_2lYI/default.jpg"));
        DEMOS_MOCK.add(new Artwork("e_68cdcRfw8", "Semua Tentang Kita - Peterpan (Cover) | Covernya Jeha", "Wardrobe by : GREENLIGHT http://www.thisisgreenlight.com THANK YOU FOR WATCHING xx. - My Link's - Instagram : http://instagram.com/Hanggini Twitter ...", "https://i.ytimg.com/vi/e_68cdcRfw8/default.jpg"));
        DEMOS_MOCK.add(new Artwork("A9N-qBAhPcA", "Tuyển Tập Những Bài Hát EDM Hay Nhất Của J.Fla Cover 2017 - Đừng Nghe Nghiện Đấy", "Những Bài Hát EDM Hay Nhất Của J.Fla Cover 2017 - J.Fla tên thật là Kim JungHwa, 29 tuổi, đến từ Seoul, Hàn Quốc. Cách đây 6 năm, cô bắt đầu sự nghiệp ...", "https://i.ytimg.com/vi/A9N-qBAhPcA/default.jpg"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demos, container, false);
        demos = new ArrayList<>();
        demosRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_demos);
        demosAdapter = new DemosAdapter(demos);
        demosLayoutManager = new LinearLayoutManager(view.getContext());
        demosRecyclerView.setAdapter(demosAdapter);
        demosRecyclerView.setLayoutManager(demosLayoutManager);
        updateData();
        return view;
    }

    private void updateData() {
        if (MOCK_DATA) {
            demos = DEMOS_MOCK;
            demosAdapter.setDemos(demos);
            demosAdapter.notifyDataSetChanged();
            return;
        }
    }
}
