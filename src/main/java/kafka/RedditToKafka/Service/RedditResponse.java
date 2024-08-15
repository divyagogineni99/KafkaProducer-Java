package kafka.RedditToKafka.Service;


import java.util.List;
//    Getters and Setters: Methods to read and update private variables.
//    Encapsulation: Protects the internal state of an object.
//    Validation: Allows for data validation when setting values.
//    Controlled Access: Provides control over how variables are accessed and modified.
//    When to Use: Use them for private variables that need to be accessed or modified from outside the class.
//
//By using getters and setters,
// you ensure that your class variables are protected and
// that you have control over how they are accessed and modified.

public class RedditResponse {
    //Purpose: Represents the overall structure of the Reddit API response.
    //Field data: Contains an instance of the Data class, which holds the actual posts.
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        //Purpose: Represents the main part of the Reddit API response that holds a list of posts.
        //Field children: A list of Child objects, each representing a post.
        private List<Child> children;

        public List<Child> getChildren() {
            return children;
        }

        public void setChildren(List<Child> children) {
            this.children = children;
        }

        public static class Child {
            //Purpose: Represents each individual post.
            //Field data: Contains an instance of PostData, which holds the details of the post.
            private PostData data;

            public PostData getData() {
                return data;
            }

            public void setData(PostData data) {
                this.data = data;
            }
        }

        public static class PostData {
            //Holds the specific details of a post, like the title and URL.
            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
//Outer Class (RedditResponse): Represents the entire response from the Reddit API.
//Inner Class Data: Holds a list of posts (children).
//Inner Class Child: Represents each post.
//Inner Class PostData: Holds the title and URL of each post.
