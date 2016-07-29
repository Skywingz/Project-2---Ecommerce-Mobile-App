package immersive.android.assembly.general.project2ecommerceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Skywingz on 7/28/16.
 */
public class DetailTabFragment extends Fragment {

    private int tabPosition;
    private String heroName;
    private ReviewRecyclerAdapter reviewAdapter;



    public static Fragment getInstance(int tab, String name) {
        DetailTabFragment fragment = new DetailTabFragment();
        fragment.tabPosition = tab;
        fragment.heroName = name;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch(tabPosition) {
            case 0:
                return inflater.inflate(R.layout.detail_fragment_details, container, false);
            case 1:
                return inflater.inflate(R.layout.detail_fragment_story, container, false);
            case 2:
                return inflater.inflate(R.layout.detail_fragment_reviews, container, false);
            default: return null;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Hero hero = HeroManager.getInstance(getActivity()).getSpecificHero(heroName);

        switch(tabPosition) {
            case 0:
                TrustMastery mastery = HeroManager.getInstance(getActivity()).getHeroTrustMastery(heroName);

                ImageView icon = (ImageView) view.findViewById(R.id.detailDetailsIcon);
                ImageView star1 = (ImageView) view.findViewById(R.id.detailDetailsRatingStar1);
                ImageView star2 = (ImageView) view.findViewById(R.id.detailDetailsRatingStar2);
                ImageView star3 = (ImageView) view.findViewById(R.id.detailDetailsRatingStar3);
                ImageView star4 = (ImageView) view.findViewById(R.id.detailDetailsRatingStar4);
                ImageView star5 = (ImageView) view.findViewById(R.id.detailDetailsRatingStar5);
                TextView name = (TextView) view.findViewById(R.id.characterDetailName);
                TextView origin = (TextView) view.findViewById(R.id.characterDetailOriginEntry);
                TextView role = (TextView) view.findViewById(R.id.characterDetailRoleEntry);
                TextView job = (TextView) view.findViewById(R.id.characterDetailJobEntry);
                TextView race = (TextView) view.findViewById(R.id.characterDetailRaceEntry);
                TextView gender = (TextView) view.findViewById(R.id.characterDetailGenderEntry);

                ImageView tmIcon = (ImageView) view.findViewById(R.id.characterDetailTrustMasteryIcon);
                TextView tmName = (TextView) view.findViewById(R.id.characterDetailTrustMasteryName);
                TextView tmInfo = (TextView) view.findViewById(R.id.characterDetailTrustMasteryDescription);

                icon.setImageResource(getActivity().getResources().getIdentifier(hero.getIcon(), "drawable", getActivity().getPackageName()));
                name.setText(hero.getName());
                origin.setText(hero.getOrigin());
                role.setText(hero.getRole());
                job.setText(hero.getJob());
                race.setText(hero.getRace());
                gender.setText(hero.getGender());

                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);
                star4.setVisibility(View.VISIBLE);
                star5.setVisibility(View.VISIBLE);
                switch(hero.getRarity()) {
                    case 2:
                        star3.setVisibility(View.GONE);
                        star4.setVisibility(View.GONE);
                        star5.setVisibility(View.GONE);
                        break;
                    case 3:
                        star4.setVisibility(View.GONE);
                        star5.setVisibility(View.GONE);
                        break;
                    case 4:
                        star5.setVisibility(View.GONE);
                        break;
                    default: break;
                }

                if (mastery.getIcon().equals("n/a")) {
                    tmIcon.setVisibility(View.GONE);
                    tmInfo.setVisibility(View.GONE);
                    tmName.setText(mastery.getName());
                } else {
                    tmIcon.setImageResource(getActivity().getResources().getIdentifier(mastery.getIcon(), "drawable", getActivity().getPackageName()));
                    tmName.setText(mastery.getName());
                    tmInfo.setText(getActivity().getResources().getIdentifier(mastery.getInfo(), "string", getActivity().getPackageName()));
                }

                break;
            case 1:
                TextView story = (TextView) view.findViewById(R.id.detailStoryFragmentTV);
                story.setText(getActivity().getResources().getIdentifier(hero.getBackStory(), "string", getActivity().getPackageName()));
                break;
            case 2:
                HeroManager.getInstance(getActivity()).loadHeroReviews(heroName);
                ArrayList<Review> reviews = HeroManager.getInstance(getActivity()).getReviews();

                RecyclerView recycler = (RecyclerView) view.findViewById(R.id.detailReviewRecyclerView);
                FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.detailReviewFAB);
                final TextView totalRating = (TextView) view.findViewById(R.id.detailReviewRating);
                final TextView totalReviews = (TextView) view.findViewById(R.id.detailReviewTotalReviews);
                double average = 0.0;
                if (reviews.size() > 0) {
                    for (Review review : reviews) {average += Double.parseDouble(review.getUserRating());}
                    average = average / (double)reviews.size();
                }
                totalRating.setText(String.format("%.1f", average));
                totalReviews.setText(String.valueOf(reviews.size()));

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recycler.setLayoutManager(layoutManager);

                reviewAdapter = new ReviewRecyclerAdapter(reviews);

                recycler.setAdapter(reviewAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_enter_user_review, null);
                        final EditText userName = (EditText) dialogView.findViewById(R.id.popupAddReviewName);
                        final EditText userComment = (EditText) dialogView.findViewById(R.id.popupAddReviewComment);
                        final RatingBar ratingBar = (RatingBar) dialogView.findViewById(R.id.popupAddReviewRatingBar);
                        TextView cancel = (TextView) dialogView.findViewById(R.id.popupAddReviewCancelButton);
                        TextView rate = (TextView) dialogView.findViewById(R.id.popupAddReviewRateButton);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setView(dialogView);

                        final AlertDialog dialog = builder.create();

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        rate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (userName.getText().toString().trim().length() == 0) { // I'm allowing users to not have to put a comment
                                    if (userName.getText().toString().trim().length() == 0) {
                                        userName.setError("This field cannot be blank");
                                    }
                                } else {
                                    String user = userName.getText().toString().trim();
                                    String comment = userComment.getText().toString().trim();
                                    double stars = (double)ratingBar.getRating();

                                    HeroManager.getInstance(getActivity()).addHeroReview(user, comment, String.valueOf(stars), heroName);

                                    totalReviews.setText(String.valueOf(HeroManager.getInstance(getActivity()).getTotalReviews()));
                                    totalRating.setText(HeroManager.getInstance(getActivity()).getAverageReviewRating());

                                    reviewAdapter.notifyDataSetChanged();

                                    dialog.dismiss();
                                }
                            }
                        });



                        dialog.show();
                    }
                });

                break;
            default: break;
        }

    }
}
