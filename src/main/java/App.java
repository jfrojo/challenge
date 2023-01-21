
/*
the problem:

given any arbitrary tree (connected, not balanced, not binary, not sorted on values)

     o
     |
     o
    / \
   o   o
  /|\   \
 o o A   o
/       / \
o       o   o
      /|\
     o B o


assume you have for each node in tree a node object (psuedocode here because they choose the language):
Node object:
 parent:    Node
 children:  List<Node>
 id:        int   (a unique identifier, if you need it - these id's are unsorted and do not mean anything, but are unique so you can use it to test for node equality)

write the method:
List<Node> findpath( Node A, Node B )
which takes parameters start node A, end node B, and returns an ordered list of nodes
that begins with A, ends with B, in between has the nodes on the unique path from A to B

example:
     1
     |
     2
    / \
   3   7
  /|\   \
 4 A 6   8
/       / \
5       B   9
      /|\
     C D E

findpath(A,B) returns [ A,3,2,7,8,B ]

You can assume when coding a solution that A and B are always nodes in the same tree.  Also note that you do not know the root of the tree, just A and B.

/*
test code:
This example builds a sample tree like this:

     0
    / \
   1   2
      / \
     3   4
     |
     5


in this test code, node constructor is taking parent and id as arguments.  id is a int.
a null parent mean that node is root.

 $root = new Node(null,0);
 $one  = new Node($root,1);
 $two  = new Node($root,2);
 $three= new Node($two,3);
 $four = new Node($two,4);
 $five = new Node($three,5);

 $root->AddChild($one);
 $root->AddChild($two);
 $two->AddChild($three);
 $two->AddChild($four);

*/

import java.util.*;

class App {

    public static void main(String[] args) {
        final ProfilerService profilerService = new ProfilerService(1000000);

        final var root = new Node<>(null, 0);
        final var one  = new Node<>(root,1);
        final var two  = new Node<>(root,2);
        final var three= new Node<>(two,3);
        final var four = new Node<>(two,4);
        final var five = new Node<>(three,5);

        root.addChildren(Set.of(one, two));
        two.addChildren(Set.of(three, four));
        three.addChildren(Set.of(five));

        final var pathFinderDFS = new DFSPathServiceImpl<Integer>();
        final var pathFinderCR = new CommonRootPathFinderImpl<Integer>();
        final var pathFinderCR2 = new CommonRootPathFinderImpl2<Integer>();

        final var profilerResultDFS = profilerService.profile(pathFinderDFS, one, five);
        final var profilerResultCR = profilerService.profile(pathFinderCR, one, five);
        final var profilerResultCR2 = profilerService.profile(pathFinderCR2, one, five);

        System.out.println("[Old] result: " + profilerResultDFS.result()
                + " [avg: " + profilerResultDFS.elapsedAvg()
                + "ms | total: " + profilerResultDFS.total() + "ms]");

        System.out.println("[New] result: " + profilerResultCR.result()
                + " [avg: " + profilerResultCR.elapsedAvg()
                + "ms | total: " + profilerResultCR.total() + "ms]");

        System.out.println("[New] result: " + profilerResultCR2.result()
                + " [avg: " + profilerResultCR2.elapsedAvg()
                + "ms | total: " + profilerResultCR2.total() + "ms]");
    }

}