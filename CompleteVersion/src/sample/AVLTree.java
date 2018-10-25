package sample;

import java.io.*;
import java.util.Stack;

class Node<T> {
    private T data;
    private int height;
    private Node<T> leftNode;
    private Node<T> rightNode;

    Node(T data) {
        this.data = data;
        this.height = 1;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    Node<T> getLeftNode() {
        return leftNode;
    }

    void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
    }

    Node<T> getRightNode() {
        return rightNode;
    }

    void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
    }

    static int max(int a, int b) {
        return (a > b)?a:b;
    }

    Node<T> minNode() {
        Node<T> temp = this;
        while (temp.getLeftNode() != null) {
            temp = temp.getLeftNode();
        }
        return temp;
    }

    Node<T> leftRotate() {
        Node<T> B = this.rightNode;
        this.rightNode = B.leftNode;
        B.leftNode = this;

        this.height = max((this.leftNode == null?0:this.leftNode.height) + 1, (this.rightNode == null?0:this.rightNode.height) + 1);
        B.height = max(B.leftNode.height, (B.rightNode == null?0:B.rightNode.height)) + 1;

        return B;
    }

    Node<T> rightRotate() {
        Node<T> B = this.leftNode;
        this.leftNode = B.rightNode;
        B.rightNode = this;

        this.height = max((this.leftNode == null?0:this.leftNode.height) + 1, (this.rightNode == null?0:this.rightNode.height) + 1);
        B.height = max((B.leftNode == null?0:B.leftNode.height), B.rightNode.height) + 1;

        return B;
    }
}

public class AVLTree {
    private Node<Word> root;

    private Node<Word> insert(Node<Word> wordNode, Word word) {
        if (wordNode == null) {
            return (new Node<>(word));
        }

        int compare = word.compareTo(wordNode.getData());
        if (compare < 0) {
            wordNode.setLeftNode(insert(wordNode.getLeftNode(), word));
        } else if (compare > 0) {
            wordNode.setRightNode(insert(wordNode.getRightNode(), word));
        } else {
            return wordNode;
        }

        wordNode.setHeight(Node.max((wordNode.getLeftNode() == null?0:wordNode.getLeftNode().getHeight()) + 1, (wordNode.getRightNode() == null?0:wordNode.getRightNode().getHeight()) + 1));

        int balance = (wordNode.getLeftNode() == null?0:wordNode.getLeftNode().getHeight()) - (wordNode.getRightNode() == null?0:wordNode.getRightNode().getHeight());
        if (balance > 1) {
            int _compare = word.compareTo(wordNode.getLeftNode().getData());
            if (_compare < 0) {
                return wordNode.rightRotate();
            } else {
                wordNode.setLeftNode(wordNode.getLeftNode().leftRotate());
                return wordNode.rightRotate();
            }
        } else if (balance < -1) {
            int _compare = word.compareTo(wordNode.getRightNode().getData());
            if (_compare > 0) {
                return wordNode.leftRotate();
            } else {
                wordNode.setRightNode(wordNode.getRightNode().rightRotate());
                return wordNode.leftRotate();
            }
        }

        return wordNode;
    }

    private Node<Word> delete(Node<Word> wordNode, Word word) {
        if (wordNode == null) {
            return wordNode;
        }

        int compare = word.compareTo(wordNode.getData());
        if (compare < 0) {
            wordNode.setLeftNode(delete(wordNode.getLeftNode(), word));
        } else if (compare > 0) {
            wordNode.setRightNode(delete(wordNode.getRightNode(), word));
        } else {
            if (wordNode.getLeftNode() == null || wordNode.getRightNode() == null) {
                Node<Word> bTemp = null;
                if (bTemp == wordNode.getLeftNode()) {
                    bTemp = wordNode.getRightNode();
                } else {
                    bTemp = wordNode.getLeftNode();
                }

                if (bTemp == null) {
                    bTemp = wordNode;
                    wordNode = null;
                } else {
                    wordNode = bTemp;
                }
            } else {
                Node<Word> temp = wordNode.getRightNode().minNode();
                wordNode.setData(temp.getData());
                wordNode.setRightNode(delete(wordNode.getRightNode(), word));
            }
        }
        if (wordNode == null) {
            return wordNode;
        }

        wordNode.setHeight(Node.max((wordNode.getLeftNode() == null ? 0 : wordNode.getLeftNode().getHeight()) + 1, (wordNode.getRightNode() == null ? 0 : wordNode.getRightNode().getHeight()) + 1));

        int balance = (wordNode.getLeftNode() == null ? 0 : wordNode.getLeftNode().getHeight()) - (wordNode.getRightNode() == null ? 0 : wordNode.getRightNode().getHeight());
        if (balance > 1) {
            if ((wordNode.getLeftNode().getLeftNode() == null ? 0 : wordNode.getLeftNode().getLeftNode().getHeight()) - (wordNode.getLeftNode().getRightNode() == null ? 0 : wordNode.getLeftNode().getRightNode().getHeight()) >= 0) {
                return wordNode.rightRotate();
            } else {
                wordNode.setLeftNode(wordNode.getLeftNode().leftRotate());
                return wordNode.rightRotate();
            }
        } else if (balance < -1) {
            if ((wordNode.getRightNode().getLeftNode() == null ? 0 : wordNode.getRightNode().getLeftNode().getHeight()) - (wordNode.getRightNode().getRightNode() == null ? 0 : wordNode.getRightNode().getRightNode().getHeight()) >= 0) {
                return wordNode.leftRotate();
            } else {
                wordNode.setRightNode(wordNode.getRightNode().rightRotate());
                return wordNode.leftRotate();
            }
        }

        return wordNode;
    }


    public void insertWord(Word word) {
        root = this.insert(root, word);
    }

    public void  deleteWord(Word word) {
        root = this.delete(root, word);
    }

   public Word search(Word word) {
        int compare = 0;
        Node<Word> temp = root;
        Word result = null;
         while (true) {
             if (temp != null) {
                 compare = temp.getData().compareTo(word);
                 if (compare == 0) {
                     result = temp.getData();
                     break;
                 } else if (compare > 0) {
                     temp = temp.getLeftNode();
                 } else {
                     temp = temp.getRightNode();
                 }
             } else {
                 break;
             }
         }
         return result;
    }

    public void printInOrder() {
        Stack<Node<Word>> wordStack = new Stack<>();
        Node<Word> temp = root;
        int count = 0;

        while (!wordStack.empty() || temp != null) {
            if (temp != null) {
                wordStack.push(temp);
                temp = temp.getLeftNode();
            } else {
                temp = wordStack.pop();
                count++;
                temp.getData().print();
                temp = temp.getRightNode();
            }
        }

        System.out.println("\nThis dictionary have " + count + " words.");
    }

    public void approximateSearch(Word word) {
        Stack<Node<Word>> wordStack = new Stack<>();
        Node<Word> temp = root;

        while (!wordStack.empty() || temp != null) {
            if (temp != null) {
                wordStack.push(temp);
                temp = temp.getLeftNode();
            } else {
                temp = wordStack.pop();
                if (temp.getData().approximateCompareTo(word) == 0) {
                    Controller.list.add(temp.getData().getWord_target());
                }
                temp = temp.getRightNode();
            }
        }
    }

    public void exportToFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fileWriter);
            Stack<Node<Word>> wordStack = new Stack<>();
            Node<Word> temp = root;

            while (!wordStack.empty() || temp != null) {
                if (temp != null) {
                    wordStack.push(temp);
                    temp = temp.getLeftNode();
                } else {
                    temp = wordStack.pop();
                    temp.getData().printToFile(writer);
                    temp = temp.getRightNode();
                }
            }

            fileWriter.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}