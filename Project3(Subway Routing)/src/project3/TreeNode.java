/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.ArrayList;
import java.util.List;

/**
 *The TreeNode class is used to retrieve the shortest path. An example of this would be, the parent would be the 
 * "start"(station 1) and the "end"(station 2) would be the children. In order to find the shortest path, each of the levels and all of the possible
 * a paths are checked, when all of the possible paths are found for that level, it will continue to the next level until the "end"(station 2, children node)
 * is reached. Once that happens, the "end" will back-track to the "start" and return a the shortest path that the Node used.
 * @author Enkim
 */
public class TreeNode {

    //The "start" node, station 1.
    private final TreeNode parent;
    // The "end node, station 2.
    private List<TreeNode> children = null;
    
    private final String station;
           
    public TreeNode(String value, TreeNode p)
    {
        this.parent = p;
        this.children = new ArrayList<>();
        this.station = value;
    }

    public String getStation(){
        return this.station;
    }
    public TreeNode getParent(){
        return parent;
    }
    
    public List getChildren(){
        return children;
    }
    
    //Adds a station in the Tree.
    public TreeNode addChild(String value)
    {
        TreeNode child = new TreeNode(value, this);
        children.add(child);
        return child;
       
    }

}
