package com.bigbanana.lab.CombitionSum;

import com.google.common.collect.Lists;

import java.util.*;

public class Fix {




	public static void main(String[] args){
		Random random = new Random();


		List<TreeNode> treeNodeList = Lists.newArrayList();
		int i = 0;

		/**
		 * 第一层
		 */

		while (i++ < 100){
			treeNodeList.add(new TreeNode(i));
		}

		/**
		 * 第二层
		 */
		int length = treeNodeList.size();

		while (i++ < 10000){
			treeNodeList.add(new TreeNode(i , treeNodeList.get(random.nextInt(length)).nodeId));
		}

		length = treeNodeList.size();

		while (i++ < 1000000){
			treeNodeList.add(new TreeNode(i , treeNodeList.get(random.nextInt(length)).nodeId));
		}

		length = treeNodeList.size();

		long start = System.currentTimeMillis();


		Map<Integer,List<TreeNode>> map = new HashMap<>(length);

		for(TreeNode treeNode : treeNodeList){
			if(map.containsKey(treeNode.parentId)){
				map.get(treeNode.parentId).add(treeNode);
			}else{
				map.put(treeNode.parentId,Lists.newArrayList(treeNode));
			}
		}


		/**
		 * 构造根节点
		 */
		List<TreeNode> treeNodes = map.get(null);


		for(TreeNode node : treeNodes) {
			fullfillTree(node  , map);
		}


		System.out.println("cost "+ (System.currentTimeMillis() - start));

	}


	private static void fullfillTree(TreeNode node,Map<Integer,List<TreeNode>> map){
		Integer nodeId = node.nodeId;
		if(!map.containsKey(nodeId)){
			return ;
		}

		List<TreeNode> treeNodes = map.get(nodeId);
		node.children = treeNodes;

		for(TreeNode child : treeNodes){
			fullfillTree(child,map);
		}
	}





public static class TreeNode{
	Integer nodeId;
	Integer parentId;
	List<TreeNode> children;
	TreeNode(int nodeId,int parentId){
		this.nodeId = nodeId;
		this.parentId = parentId;
	}

	TreeNode(int nodeId){
		this.nodeId = nodeId;
		this.parentId = parentId;
	}

}


}