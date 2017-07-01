package com.example.ecss.medicalmapper.userInterface.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchBranch;
import com.example.ecss.medicalmapper.utility.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvancedSearchAdapter extends RecyclerView.Adapter<AdvancedSearchAdapter.MyViewHolder> {

    private List<AdvancedSearchBranch> mBranches ;

    Listner mListner;

    public static interface Listner {
        void onClick(int pos);
    }

    public void setListner(Listner mListner) {
        this.mListner = mListner;
    }

    public void updateAdapter(List<AdvancedSearchBranch> Branches) {
        this.mBranches = Branches;
        notifyDataSetChanged();
    }


    public AdvancedSearchAdapter() {

    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        int layoutPhotoItem = R.layout.list_branch_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutPhotoItem ,parent ,shouldAttachToParentImmediately);
        return new MyViewHolder(view); }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


            AdvancedSearchBranch branch = mBranches.get(position) ;
           // holder.BranchTypeTextView.setText(branch.getBranchType() );
            holder.PlaceNameTextView.setText((Utility.getLanguageFromSettings() == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            holder.SpecializationTextView.setText((Utility.getLanguageFromSettings() == 0) ? branch.getSpecializationEN() : branch.getSpecializationAR());

            if (branch.getBranchType().equals("clinic"))
            {
                holder.BranchTypeTextView.getContext().getString(R.string.clinic);
                holder.logo.setBackgroundResource(R.drawable.clinicitem);
            }
            else if (branch.getBranchType().equals("hospital"))
            {
                holder.BranchTypeTextView.getContext().getString(R.string.hospital);
                holder.logo.setBackgroundResource(R.drawable.hospitalitem);

            }
            else if (branch.getBranchType().equals("lab"))
            {
                holder.BranchTypeTextView.getContext().getString(R.string.laboratory);
                holder.logo.setBackgroundResource(R.drawable.labitem);

            }
            else if (branch.getBranchType().equals("pharmacy"))
            {
                holder.BranchTypeTextView.getContext().getString(R.string.pharmacy);
                holder.logo.setBackgroundResource(R.drawable.pharmacyitem);

            }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListner != null) {
                        mListner.onClick(position);
                    }
                }
            });

    }


    @Override
    public int getItemCount() {
        return mBranches == null ? 0 : mBranches.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.branch_type_textview)
        TextView BranchTypeTextView;

        @BindView(R.id.place_name_textview)
        TextView PlaceNameTextView;

        @BindView(R.id.specialization_textview)
        TextView SpecializationTextView;

        @BindView(R.id.search_cardview)
        CardView cardView;

        @BindView(R.id.logob)
        ImageView logo;

      //  private AdvancedSearchBranch mBranch;

        MyViewHolder(View convertView) {
            super(convertView);

            ButterKnife.bind(this, convertView);
        }

       /* void setContent(AdvancedSearchBranch Branch) {
            mBranch = Branch;

            BranchTypeTextView.setText(Branch.getBranchType() );
            PlaceNameTextView.setText(Branch.getPlaceNameEN());
            SpecializationTextView.setText(Branch.getSpecializationEN());
        }*/
    }
}
