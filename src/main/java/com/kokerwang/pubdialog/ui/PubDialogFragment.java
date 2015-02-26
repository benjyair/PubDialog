package com.kokerwang.pubdialog.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kokerwang.pubdialog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * PubDialogFragment
 * 使用时对应的Activity实现ItemClickListener接口处理回调
 * <p/>
 * Created by KokerWang on 15/2/26.
 */
public class PubDialogFragment extends DialogFragment {

    private List<DialogGroup> dialogGroups;

    private ItemClickListener itemClickListener;

    /**
     * 使用该DialogFragment 的Activity需要实现该接口来处理事件回调
     */
    public interface ItemClickListener {
        /**
         * 点击事件回调
         *
         * @param clickedView  对应的View
         * @param dialogObject 对应的Item对象
         */
        public void onItemClick(View clickedView, DialogObject dialogObject);
    }


    public static PubDialogFragment newInstance(List<DialogGroup> dialogGroups) {
        PubDialogFragment pubDialogFragment = new PubDialogFragment();
        pubDialogFragment.dialogGroups = dialogGroups;
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            itemClickListener = (ItemClickListener) activity;
        } catch (ClassCastException e) {
            itemClickListener = null;
        }
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
        return rootView;
    }

    LinearLayout ll_dialog;

    private void initViews(View view) {
        ll_dialog = (LinearLayout) view.findViewById(R.id.ll_dialog);

        ll_dialog.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        ll_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PubDialogFragment.this.dismiss();
            }
        });
        for (DialogGroup dialogGroup : dialogGroups) {
            for (int i = 0; i < dialogGroup.size(); i++) {
                ll_dialog.addView(dataToView(dialogGroup.get(i), getItemLocation(i, dialogGroup.size())));
            }
        }

        ll_dialog.post(new Runnable() {
            @Override
            public void run() {
                Animation am = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_in);
                ll_dialog.startAnimation(am);
            }
        });
    }


    @Override
    public void dismiss() {
        ll_dialog.post(new Runnable() {
            @Override
            public void run() {
                Animation am = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_out);
                am.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        PubDialogFragment.super.dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                ll_dialog.startAnimation(am);
            }
        });
    }

    /**
     * 数据转换为view
     *
     * @param dialogObject
     * @param itemLocation
     * @return
     */
    public View dataToView(final DialogObject dialogObject, ItemLocation itemLocation) {
        View ll_item = LinearLayout.inflate(getActivity(), R.layout.lay_item, null);
        if (itemClickListener != null) {
            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, dialogObject);
                }
            });
        }

        int dp10 = (int) getActivity().getResources().getDimension(R.dimen.def_gap);

        //设置间距
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

        //初始化背景

        if (dialogObject.getBgId() != 0) {
            ll_item.setBackgroundResource(dialogObject.getBgId());
        } else {
            //设置默认背景
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

        //初始化组件

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
     * 根据item所在位置计算背景的类型
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

}
