package com.unnamed.b.atv.view;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.unnamed.b.atv.DragLinearLayout;
import com.unnamed.b.atv.R;

/**
 * Created by Bogdan Melnychuk on 2/10/15.
 */
public class TreeNodeWrapperView extends LinearLayout {
    private DragLinearLayout nodeItemsContainer;
    private ViewGroup nodeContainer;
    private final int containerStyle;

    public TreeNodeWrapperView(Context context, int containerStyle) {
        super(context);
        this.containerStyle = containerStyle;
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);

        nodeContainer = new RelativeLayout(getContext());
        nodeContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        nodeContainer.setId(R.id.node_header);

        ContextThemeWrapper newContext = new ContextThemeWrapper(getContext(), containerStyle);
        nodeItemsContainer = new DragLinearLayout(newContext, null, containerStyle);
        nodeItemsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        nodeItemsContainer.setId(R.id.node_items);
        nodeItemsContainer.setOrientation(LinearLayout.VERTICAL);
        nodeItemsContainer.setVisibility(View.GONE);

        addView(nodeContainer);
        addView(nodeItemsContainer);
        for(int i = 0; i < nodeItemsContainer.getChildCount(); i++){
            View child = nodeItemsContainer.getChildAt(i);
            // the child will act as its own drag handle
            nodeItemsContainer.setViewDraggable(child, child);
        }
        nodeItemsContainer.setOnViewSwapListener(new DragLinearLayout.OnViewSwapListener() {
            @Override
            public void onSwap(View firstView, int firstPosition,
                               View secondView, int secondPosition) {
                // update data, etc..
            }
        });
    }


    public void insertNodeView(View nodeView) {
        nodeContainer.addView(nodeView);
    }

    public ViewGroup getNodeContainer() {
        return nodeContainer;
    }
}
