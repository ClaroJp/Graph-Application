import java.util.*;

public class SocialNetworkManager {
    private final Map<String, Set<String>> socialGraph = new HashMap<>();

    public void addUser(String user) {
        socialGraph.putIfAbsent(user, new HashSet<>());
    }

    public void addFriendship(String user1, String user2) {
        if (socialGraph.containsKey(user1) && socialGraph.containsKey(user2)) {
            socialGraph.get(user1).add(user2);
            socialGraph.get(user2).add(user1);
            System.out.println("Friendship created between " + user1 + " and " + user2 + ".");
        } else {
            System.out.println("One or both users do not exist.");
        }
    }

    public void viewAllUsers() {
        if (socialGraph.isEmpty()) {
            System.out.println("No users in the network.");
        } else {
            System.out.println("Users in the network: " + socialGraph.keySet());
        }
    }

    public void viewFriendships(String user) {
        if (socialGraph.containsKey(user)) {
            System.out.println(user + "'s friends: " + socialGraph.get(user));
        } else {
            System.out.println("User does not exist.");
        }
    }

    public void suggestFriends(String user) {
        if (!socialGraph.containsKey(user)) {
            System.out.println("User does not exist.");
            return;
        }

        Set<String> friends = socialGraph.get(user);
        Set<String> suggestions = new HashSet<>();

        for (String friend : friends) {
            for (String friendOfFriend : socialGraph.get(friend)) {
                if (!friendOfFriend.equals(user) && !friends.contains(friendOfFriend)) {
                    suggestions.add(friendOfFriend);
                }
            }
        }

        if (suggestions.isEmpty()) {
            System.out.println("No friend suggestions for " + user + ".");
        } else {
            System.out.println("Friend suggestions for " + user + ": " + suggestions);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetworkManager manager = new SocialNetworkManager();

        System.out.println("Welcome to the Social Network Manager!");
        while (true) {
            System.out.println("\n1. Add User\n2. Add Friendship\n3. View All Users\n4. View Friendships\n5. Suggest Friends\n6. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter username: ");
                        String user = scanner.nextLine();
                        manager.addUser(user);
                        System.out.println("User added!");
                    }
                    case 2 -> {
                        System.out.print("Enter first username: ");
                        String user1 = scanner.nextLine();
                        System.out.print("Enter second username: ");
                        String user2 = scanner.nextLine();
                        manager.addFriendship(user1, user2);
                    }
                    case 3 -> manager.viewAllUsers();
                    case 4 -> {
                        System.out.print("Enter username: ");
                        String user = scanner.nextLine();
                        manager.viewFriendships(user);
                    }
                    case 5 -> {
                        System.out.print("Enter username: ");
                        String user = scanner.nextLine();
                        manager.suggestFriends(user);
                    }
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
    }
}
