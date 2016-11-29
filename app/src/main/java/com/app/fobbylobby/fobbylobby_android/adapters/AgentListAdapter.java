package com.app.fobbylobby.fobbylobby_android.adapters;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.fobbylobby.fobbylobby_android.R;
import com.app.fobbylobby.fobbylobby_android.models.Agent;

import java.util.List;

public class AgentListAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Agent> agentList;

    public AgentListAdapter(Activity context, List<Agent> agentList) {
        super(context, R.layout.agent_list_item, agentList);
        this.context = context;
        this.agentList = agentList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewSingle = inflater.inflate(R.layout.agent_list_item, null, true);
        TextView ListAgentName = (TextView)
                ListViewSingle.findViewById(R.id.list_item_name);
        TextView listAgentAddress = (TextView)
                ListViewSingle.findViewById(R.id.list_item_address);
        TextView listViewItemId = (TextView)
                ListViewSingle.findViewById(R.id.list_item_id);
        RatingBar listAgentRating = (RatingBar) ListViewSingle.findViewById(R.id.list_item_rating);

        Agent currentAgent = agentList.get(position);
        ListAgentName.setText(currentAgent.getName());
        listAgentAddress.setText(currentAgent.getAddress());
        listAgentRating.setRating((float) currentAgent.getRating());

        return ListViewSingle;
    }
}
