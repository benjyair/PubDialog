package com.kokerwang.pubdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * PubDialogFragment
 * When in use, the corresponding Activity implementation of the ItemClickListener interface processing callback
 * <p/>
 * Created by KokerWang on 15/2/26.
 */
public class PubDialogFragment extends DialogFragment {

    private List<DialogGroup> dialogGroups;

    private ItemClickListener itemClickListener;

    /**
     * Using the DialogFragment Activity needs to implement the interface to handle the event callback
     */
    public interface ItemClickListener {

        public void onItemClick(View clickedView, DialogObject dialogObject, int groupIndex, int itemIndex);
    }


    public static PubDialogFragment newInstance(List<DialogGroup> dialogGroups) {
        PubDialogFragment pubDialogFragment = new PubDialogFragment();
        pubDialogFragment.dialogGroups = dialogGroups;
        return pubDialogFragment;
    }

    public static PubDialogFragment newInstance(DialogGroup... dialogGroups) {
        PubDialogFragment pubDialogFragment = new PubDialogFragment();
        pubDialogFragment.dialogGroups = new ArrayList<>(dialogGroups.length);
        for (DialogGroup group : dialogGroups) {
            pubDialogFragment.dialogGroups.add(group);
        }
        return pubDialogFragment;
    }

    public static PubDialogFragment newInstance(List<String> strings, boolean isSingle) {
        PubDialogFragment pubDialogFragment = new PubDialogFragment();
        pubDialogFragment.dialogGroups = new ArrayList<>(strings.size());
        if (isSingle) {
            for (String str : strings) {
                pubDialogFragment.dialogGroups.add(new DialogGroup(new DialogObject(str)));
            }
        } else {
            pubDialogFragment.dialogGroups.add(new DialogGroup(strings));
        }
        return pubDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.DialogFragmentStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        initViews(rootView);
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (getDialog().isShowing() && !isAnim) {
                        dismissWithAnim();
                    }
                    return true;
                }
                return false;
            }
        });
        return rootView;
    }

    private LinearLayout ll_dialog;
    private Animation in_anim;

    private void initViews(View view) {
        ll_dialog = (LinearLayout) view.findViewById(R.id.ll_dialog);


        ll_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissWithAnim();
            }
        });

        for (int j = 0; j < dialogGroups.size(); j++) {
            DialogGroup dialogGroup = dialogGroups.get(j);
            for (int i = 0; i < dialogGroup.size(); i++) {
                ll_dialog.addView(dataToView(dialogGroup.get(i), j, i, getItemLocation(i, dialogGroup.size())));
            }
        }

        ll_dialog.post(new Runnable() {
            @Override
            public void run() {
                if (in_anim == null) {
                    in_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_in);
                }
                ll_dialog.startAnimation(in_anim);
            }
        });
    }

    private Animation out_anim;
    private boolean isAnim = false;

    /**
     * By the way of animation dismiss dialog
     */
    public void dismissWithAnim() {
        if (out_anim == null) {
            out_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_out);
        }
        out_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnim = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnim = false;
                ll_dialog.post(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ll_dialog.startAnimation(out_anim);
    }

    /**
     * Data conversion for view
     *
     * @param dialogObject
     * @param itemLocation
     * @return
     */
    public View dataToView(final DialogObject dialogObject, final int groupIndex, final int itemIndex, ItemLocation itemLocation) {
        View ll_item = LinearLayout.inflate(getActivity(), R.layout.lay_item, null);
        if (itemClickListener != null) {
            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, dialogObject, groupIndex, itemIndex);
                }
            });
        }

        int dp10 = (int) getActivity().getResources().getDimension(R.dimen.def_gap);

        //set gap
        switch (itemLocation) {
            case CENTER:
            case BOTTOM:
                break;
            case TOP:
            case ALONE:
            default:
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, dp10, 0, 0);
                ll_item.setLayoutParams(lp);
                break;
        }

        //init background

        if (dialogObject.getBgId() != 0) {
            ll_item.setBackgroundResource(dialogObject.getBgId());
        } else {
            //set default background
            switch (itemLocation) {
                case TOP:
                    ll_item.setBackgroundResource(R.drawable.item_top);
                    break;
                case CENTER:
                    ll_item.setBackgroundResource(R.drawable.item_center);
                    break;
                case BOTTOM:
                    ll_item.setBackgroundResource(R.drawable.item_bottom);
                    break;
                case ALONE:
                default:
                    ll_item.setBackgroundResource(R.drawable.item_alone);
                    break;
            }
        }
        ll_item.setPadding(dp10, dp10, dp10, dp10);

        //init view

        //name
        TextView textView = (TextView) ll_item.findViewById(R.id.tv_name);
        textView.setText(dialogObject.getName());

        //textColor
        if (dialogObject.getTextColor() != 0) {
            textView.setTextColor(dialogObject.getTextColor());
        }

        //icon
        if (dialogObject.getImgSrc() != 0) {
            ImageView img = (ImageView) ll_item.findViewById(R.id.img);
            img.setImageResource(dialogObject.getImgSrc());
            img.setVisibility(View.VISIBLE);
        }


        return ll_item;
    }

    /**
     * Depending on the type of item location computing background
     *
     * @param index
     * @param size
     * @return
     */
    private ItemLocation getItemLocation(int index, int size) {
        if (size == 1) {
            return ItemLocation.ALONE;
        }
        if (index == 0) {
            return ItemLocation.TOP;
        }
        if (index == size - 1) {
            return ItemLocation.BOTTOM;
        }
        return ItemLocation.CENTER;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
