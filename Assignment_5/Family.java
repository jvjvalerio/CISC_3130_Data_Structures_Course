import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Family {

    MemberNode root;

    public static void main(String[] args) {

        Family family = new Family();
        family.constructFamilyTree();
        // family.printFamilyTree(rootMember);

        family.retrieveFather("Michael");
        family.retrieveSons("Bob");
        family.retrieveBrothers("Richard", null);
        family.retrieveOldestBrother("Bob");
        family.retrieveYoungestBrother("Bob");
        family.retrieveOldestSon("Bob");
        family.retrieveYoungestSon("Bob");
        family.retrieveUncles("Jake");
        family.retrieveGrandfather("Bill");

    }

    // construct family tree
    private MemberNode constructFamilyTree() {

        String data = getInput();
        String[] lines = data.split("\n");
        Set<MemberNode> memberSet = new HashSet<>();

        int k = 0;
        for (int i = 0; i < lines.length; i++) {

            String[] nameCount = lines[i].split(" ");
            String person = nameCount[0];
            int count = Integer.parseInt(nameCount[1]);

            MemberNode parent = new MemberNode(person);
            if (memberSet.contains(parent) && count == 0) {
                continue;
            }
            for (MemberNode member : memberSet) {
                if (member.equals(parent)) {
                    parent = member;
                    break;
                }
            }
            memberSet.add(parent);
            if (i == 0) {
                root = parent;
            }
            for (int j = k + 1; j <= k + count; j++) {
                int childIndex = j;
                String[] nameCountChild = lines[childIndex].split(" ");
                MemberNode child = new MemberNode(nameCountChild[0]);
                memberSet.add(child);
                parent.childs.add(child);
            }
            k = k + count;
        }

        return root;
    }

    // Who is the father of p?
    private void retrieveFather(String member) {

        if (root == null)
            return;
        Queue<MemberNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                MemberNode node = queue.poll();
                for (MemberNode item : node.childs) {
                    if (item.name.equals(member)) {
                        String prefix = "#Father";
                        System.out.println(prefix + " of " + member + " - ");
                        System.out.println(node.name + " ");
                        break;
                    }
                    queue.offer(item);
                }
            }
        }
    }

    // Who are all the sons of p?
    private void retrieveSons(String member) {

        if (root == null)
            return;

        Queue<MemberNode> q = new LinkedList<MemberNode>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            while (n > 0) {
                MemberNode p = q.peek();
                q.remove();
                if (p.name.equals(member)) {
                    System.out.println("#Sons of " + member + " - ");

                    for (int i = 0; i < p.childs.size(); i++)
                        System.out.println(p.childs.get(i).name);
                }
                for (int i = 0; i < p.childs.size(); i++)
                    q.add(p.childs.get(i));
                n--;
            }
        }
    }

    // Who are all the brothers of p?
    private void retrieveBrothers(String member, String replace) {

        if (root == null)
            return;
        Queue<MemberNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                MemberNode node = queue.poll();
                for (MemberNode item : node.childs) {
                    if (item.name.equals(member)) {
                        String prefix = null;
                        if (replace != null) {
                            prefix = replace;
                        } else {
                            prefix = "#Brothers";
                        }
                        if (replace == null)
                            System.out.println(prefix + " of " + member + " - ");
                        for (MemberNode match : node.childs) {
                            if (!match.name.equals(member)) {
                                System.out.println(match.name + " ");
                            }
                        }
                        break;
                    }
                    queue.offer(item);
                }
            }
        }
    }

    // Who is the oldest brother of p?
    private void retrieveOldestBrother(String member) {

        if (root == null)
            return;
        Queue<MemberNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                MemberNode node = queue.poll();
                for (MemberNode item : node.childs) {
                    if (item.name.equals(member)) {
                        System.out.println("#Oldest brother of " + member + " - ");
                        System.out.println(node.childs.getFirst().name);
                        break;
                    }
                    queue.offer(item);
                }
            }
        }
    }

    // Who is the youngest brother of p?
    private void retrieveYoungestBrother(String member) {

        if (root == null)
            return;
        Queue<MemberNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                MemberNode node = queue.poll();
                for (MemberNode item : node.childs) {
                    if (item.name.equals(member)) {
                        System.out.println("#Youngest brother of " + member + " - ");
                        System.out.println(node.childs.getLast().name);
                        break;
                    }
                    queue.offer(item);
                }
            }
        }
    }

    // Who is the oldest son of p?
    private void retrieveOldestSon(String member) {

        if (root == null)
            return;

        Queue<MemberNode> q = new LinkedList<MemberNode>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            while (n > 0) {
                MemberNode p = q.peek();
                q.remove();
                if (p.name.equals(member)) {
                    System.out.println("#Oldest son of " + member + " : ");
                    System.out.println(p.childs.getFirst().name);
                    break;
                }
                for (int i = 0; i < p.childs.size(); i++)
                    q.add(p.childs.get(i));
                n--;
            }
        }
    }

    // Who is the youngest son of p?
    private void retrieveYoungestSon(String member) {

        if (root == null)
            return;

        Queue<MemberNode> q = new LinkedList<MemberNode>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            while (n > 0) {
                MemberNode p = q.peek();
                q.remove();
                if (p.name.equals(member)) {
                    System.out.println("#Youngest son of " + member + " : ");
                    System.out.println(p.childs.getLast().name);
                    break;
                }
                for (int i = 0; i < p.childs.size(); i++)
                    q.add(p.childs.get(i));
                n--;
            }
        }
    }

    // Who are the uncles of p?
    private void retrieveUncles(String member) {

        if (root == null)
            return;
        Queue<MemberNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                MemberNode node = queue.poll();
                for (MemberNode item : node.childs) {
                    if (item.name.equals(member)) {
                        System.out.println("#Uncles of " + member + " - ");
                        retrieveBrothers(node.name, "Uncles");
                        break;
                    }
                    queue.offer(item);
                }
            }
        }
    }

    // Who is the grandfather of p?
    private void retrieveGrandfather(String member) {

        if (root == null)
            return;
        Queue<MemberNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                MemberNode node = queue.poll();
                for (MemberNode item : node.childs) {

                    for (MemberNode grandchild : item.childs) {
                        if (grandchild.name.equals(member)) {
                            System.out.println("#Grandfather of " + member + " - ");
                            System.out.println(node.name);
                            break;
                        }
                    }
                    queue.offer(item);
                }
            }
        }
    }

    private String getInput() {

        StringBuilder sb = new StringBuilder();
        sb.append("Jones 3"); // (the root Jones has 3 sons)
        sb.append("\n");
        sb.append("Bob 2");
        sb.append("\n");
        sb.append("Dan 0");
        sb.append("\n");
        sb.append("Brian 1"); // (these are the 3 sons of Jones)
        sb.append("\n");
        sb.append("Richard 0");
        sb.append("\n");
        sb.append("Jake 1"); // (these are the 2 sons of Bob)
        sb.append("\n");
        sb.append("Michael 1"); // (the one son of Brian)
        sb.append("\n");
        sb.append("Bill 0"); // (the one son of Jake)
        sb.append("\n");
        sb.append("Deville 0"); // (the one son of Michael)

        return sb.toString();
    }
}

class MemberNode {

    String name;
    LinkedList<MemberNode> childs;

    MemberNode(String name) {
        this.name = name;
        this.childs = new LinkedList<>();
    }

    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MemberNode)) {
            return false;
        }

        MemberNode node = (MemberNode) obj;
        if (!this.name.equals(node.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}