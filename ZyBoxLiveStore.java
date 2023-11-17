//Description: In this page, we are implementing the options user called. Like adding, removing, searching, changing games. 

public class ZyBoxLiveStore {

    private Node root; // Binary Search Tree root node

    // Constructor
    public ZyBoxLiveStore() {
        this.root = null;
    }
    //we add the game to the store
    public Node addGameToStore(Node root, Game gameToAdd) {
        Node no = null;
        if(root == null){
            return new Node(gameToAdd);
        }
        else{
           if(root.getGame().getPrice() == gameToAdd.getPrice()){
               System.out.print("Game at this price is in store inventory already.\n");
               return this.root;
           }
           else
           if(root.getGame().getPrice() < gameToAdd.getPrice()){
               if(root.getRight() == null){
                  root.setRight(new Node(gameToAdd));
                  no = root.getRight();
               }
               else{
                  no = addGameToStore(root.getRight(), gameToAdd);
               }
           }
           else{
               if(root.getLeft() == null){
                  root.setLeft(new Node(gameToAdd));
                  no = root.getLeft();
               }
               else{
                  no = addGameToStore(root.getLeft(), gameToAdd);
               }
           }
           return this.root;   
        }
        
    }
    //we print each game in ascending order
    void listGamesByPrice(Node node){
      if(node.getLeft() != null){
         listGamesByPrice(node.getLeft());
      }
      System.out.println(node.getGame().toString());
      if(node.getRight() != null){
         listGamesByPrice(node.getRight());
      }
    }
    // * removeGameFromStore(...) METHOD PROVIDED AS PART OF TEMPLATE
    // Removes a game from the BST based on the game's price (the BST key)
    public Node removeGameFromStore(Node node, double price) {
        if (node == null) {
            return null;
        }

        double nodePrice = node.getGame().getPrice();

        if (price < nodePrice) {
            node.setLeft(removeGameFromStore(node.getLeft(), price));
        } else if (price > nodePrice) {
            node.setRight(removeGameFromStore(node.getRight(), price));
        } else {
            // Found the node to be removed
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                // Node has two children, find successor and replace node
                Node successor = findMinNode(node.getRight());
                node.setGame(successor.getGame());
                node.setRight(removeGameFromStore(node.getRight(), successor.getGame().getPrice()));
            }
        }
        return node;
    }
    // we find left most node
    private Node findMinNode(Node right) {
        if(right == null){
          return null;
        }
        else{
          Node suc;
          if(right.getLeft() == null){
            suc = right;
          }
          else{
            suc = findMinNode(right.getLeft()); 
          }
          return suc;
        }
    }
    // we have price which we need to find a game on that price
    public Game searchByPrice(Node root, double price){
      if(root == null){
         return null;
      }
      Game ans = null;
      double x = (root.getGame().getPrice());
      x = x - price;
      if(x > 0.0){
         ans = searchByPrice(root.getLeft(), price);
      }
      else
      if(x < 0.0){
         ans = searchByPrice(root.getRight(), price);
      }
      else{
         ans = root.getGame();
      }
      return ans;
    }
    // * searchByName(...) METHOD PROVIDED AS PART OF TEMPLATE
    // Searches for a game (by name) in the store. Returns null if there are no
    // games in the store or the game was not found. Otherwise, returns the Game
    // object with the game's matching name
    public Game searchByName(Node node, String name) {
        if (node == null) {
            return null;
        }
        Game leftResult = searchByName(node.getLeft(), name);
        if (leftResult != null) {
            return leftResult;
        }
        if (node.getGame().getName().equals(name)) {
            return node.getGame();
        }
        Game rightResult = searchByName(node.getRight(), name);
        return rightResult;
    }
    // we calculate how many games have in store
    public int countGamesInStore(Node node){
      if(node == null){
         return 0;
      }
      else{
         int rs = 0, ls = 0;
         if(node.getRight() != null){
            rs = countGamesInStore(node.getRight());
         }
         if(node.getLeft() != null){
            ls = countGamesInStore(node.getLeft());
         }
         return rs + ls + 1;
      }
    }
    // we calculate store value by adding price of each game
    public double calculateStoreValue(Node node){
      if(node == null){
         return 0.0;
      }
      else{
         double rs = 0.0, ls = 0.0;
         if(node.getRight() != null){
            rs += calculateStoreValue(node.getRight());
         }
         if(node.getLeft() != null){
            ls += calculateStoreValue(node.getLeft());
         }
         return rs + ls + node.getGame().getPrice();
      }
    }
    // we find most downloaded game
    public Node searchMostPopularGame(Node node){
      if(node == null){
         return null;
      }
      else{
         int mx = 0;
         Node ln = null, rn = null;
         if(node.getLeft() != null){
            ln = searchMostPopularGame(node.getLeft());
         }
         if(node.getRight() != null){
            rn = searchMostPopularGame(node.getRight());
         }
         if(ln != null && ln.getGame().getDownloads() > node.getGame().getDownloads()){
            node = ln;
         }
         if(rn != null && rn.getGame().getDownloads() > node.getGame().getDownloads()){
            node = rn;
         }
         return node;
      }
    }
    
    // * get/setRoot(...) METHODS PROVIDED AS PART OF TEMPLATE
    // getters and setters for the BST root
    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

}
