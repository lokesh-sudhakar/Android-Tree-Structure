package com.example.androidtreestructure;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * @author Lokesh chennamchetty
 * @date 21/11/2020
 */
public class TreFragment extends Fragment implements TreeNode.TreeNodeClickListener, BlockItemHolder.BlockClickListeners {

  public final static String FRAGMENT_PARAM = "fragment";
  private static final String NAME = "Very long name for folder";
  private AndroidTreeView tView;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_default, null, false);
    TreeNode root = TreeNode.root();

    TreeNode s1 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Folder with very long name ")).setViewHolder(
            new BlockItemHolder(getActivity(),this));
    TreeNode s2 = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "Another folder with very long name")).setViewHolder(
            new BlockItemHolder(getActivity(),this));

//    fillFolder(s1);
//    fillFolder(s2);

    root.addChildren(s1, s2);

    tView = new AndroidTreeView(getActivity(), root);
    tView.setDefaultAnimation(true);
    tView.setUse2dScroll(false);
    tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
    tView.setDefaultNodeClickListener(TreFragment.this);
    tView.setDefaultViewHolder(BlockItemHolder.class);
    container.addView(tView.getView());
    tView.setUseAutoToggle(false);
    tView.collapseAll();
    if (savedInstanceState != null) {
      String state = savedInstanceState.getString("tState");
      if (!TextUtils.isEmpty(state)) {
        tView.restoreState(state);
      }
    }
    return rootView;
  }

  private void fillFolder(TreeNode folder) {
    TreeNode currentNode = folder;
    for (int i = 0; i < 15; i++) {
      TreeNode file = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, NAME + " " + i)).setViewHolder(
              new BlockItemHolder(getActivity(),this));;;
      currentNode.addChild(file);
      currentNode = file;
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("tState", tView.getSaveState());
  }

  @Override
  public void onClick(TreeNode node, Object value) {
    Toast toast = Toast.makeText(getActivity(), ((IconTreeItemHolder.IconTreeItem)value).text, Toast.LENGTH_SHORT);
    toast.show();
  }

  @Override
  public void addBlock(TreeNode node) {
    TreeNode file = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "")).setViewHolder(
            new BlockItemHolder(getActivity(),this));;
    tView.addNode(node.getParent(),file);
  }

  @Override
  public void addChild(TreeNode node) {
    TreeNode file = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, "")).setViewHolder(
            new BlockItemHolder(getActivity(),this));;
    tView.addNode(node,file);
  }
}
