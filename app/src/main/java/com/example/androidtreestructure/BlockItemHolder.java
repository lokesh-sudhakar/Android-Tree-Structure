package com.example.androidtreestructure;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;

/**
 * @author Lokesh chennamchetty
 * @date 21/11/2020
 */

public class BlockItemHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {
  private TextView tvValue;
  private Button addChild;
  private Button addBlock;
  private PrintView arrowView;
  private CheckBox nodeSelector;
  private BlockClickListeners listeners;

  public BlockItemHolder(Context context) {
    super(context);
  }

  public BlockItemHolder(Context context, BlockClickListeners listener) {
    super(context);
    this.listeners = listener;
  }

  public interface BlockClickListeners{
    void addBlock(TreeNode node);
    void addChild(TreeNode node);
  }

  @Override
  public View createNodeView(final TreeNode node, IconTreeItemHolder.IconTreeItem value) {
    final View view = LayoutInflater.from(context).inflate(R.layout.layout_workflow_block, null, false);
    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            /*width*/ getScreenWidth(),
            /*height*/ ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(param);

    tvValue =  view.findViewById(R.id.flowNameTv);
    tvValue.setOnClickListener(view1 -> tView.toggleNode(node));

    addChild = view.findViewById(R.id.addChild);
    addBlock = view.findViewById(R.id.addBlock);
    addChild.setOnClickListener(view13 -> {
      if (!node.isExpanded()){
        tView.expandNode(node,false);
      }
      listeners.addChild(node);
    });
    addBlock.setOnClickListener(view12 -> {
      listeners.addBlock(node);
    });
//    tvValue.setText(value.text);
//
//    final PrintView iconView = (PrintView) view.findViewById(R.id.icon);
//    iconView.setIconText(context.getResources().getString(value.icon));

//    arrowView = (PrintView) view.findViewById(R.id.arrow_icon);
//    arrowView.setPadding(20,10,10,10);
//    if (node.isLeaf()) {
//      arrowView.setVisibility(View.GONE);
//    }
//    arrowView.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        tView.toggleNode(node);
//      }
//    });
//
//    nodeSelector = (CheckBox) view.findViewById(R.id.node_selector);
//    nodeSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//      @Override
//      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        node.setSelected(isChecked);
//        for (TreeNode n : node.getChildren()) {
//          getTreeView().selectNode(n, isChecked);
//        }
//      }
//    });
//    nodeSelector.setChecked(node.isSelected());

    return view;
  }

  public static int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  @Override
  public void toggle(boolean active) {
    tvValue.setText(active? "Expanded": "Not expanded");
  }

  @Override
  public void toggleSelectionMode(boolean editModeEnabled) {
    nodeSelector.setVisibility(editModeEnabled ? View.VISIBLE : View.GONE);
    nodeSelector.setChecked(mNode.isSelected());
  }


}