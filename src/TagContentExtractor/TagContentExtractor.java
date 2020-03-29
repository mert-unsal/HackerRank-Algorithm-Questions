package TagContentExtractor;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.UUID;

public class TagContentExtractor {
    static Tag identifyTag(String partialText, int partialTextStartingIndex) {
        Tag tag = new Tag();
        tag.setTagId(new UUID(8, 16).toString());
        int startingOfTagPosition = partialText.indexOf("<");
        // No Tag Found
        if (startingOfTagPosition == -1) {
            return null;
        }
        try{
            if (partialText.charAt(startingOfTagPosition + 1) == '/') {
                tag.isClosingTag = true;
            }
        }
        catch (StringIndexOutOfBoundsException ex){
            return null;
        }
        int endingOfTagPosition = partialText.indexOf(">");
        if (endingOfTagPosition == -1) return null;
        tag.setTagStartIndex(partialTextStartingIndex + startingOfTagPosition);
        tag.setTagEndIndex(partialTextStartingIndex + endingOfTagPosition);
        if (tag.isClosingTag()) {
            tag.setTagName(partialText.substring(startingOfTagPosition + 2, endingOfTagPosition));
        } else {
            if (endingOfTagPosition <= startingOfTagPosition) return null;
            tag.setTagName(partialText.substring(startingOfTagPosition + 1, endingOfTagPosition));
        }
        return tag;
    }

    static String catchContent(String string) {
        String returningString = "";
        if (string.startsWith("<")) {
            returningString = null;
        } else {
            int index = string.indexOf("<");
            if (index != -1) {
                returningString = string.substring(0, index);
            }
        }
        return returningString;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isValidContentFound = false;
        Stack<Tag> stackOfTags = null;
        HashMap<String, String> contentMap = null;
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            isValidContentFound = false;
            String line = in.nextLine();
            stackOfTags = new Stack<>();
            contentMap = new HashMap<>();
            for (int index = 0; index < line.length(); index++) {
                Tag tag = identifyTag(line.substring(index), index);
                if (tag == null)
                    if (!isValidContentFound) {
                        System.out.println("None");
                        break;
                    } else {
                        break;
                    }
                if (!tag.isClosingTag()) {
                    contentMap.put(tag.tagId, catchContent(line.substring(tag.getTagEndIndex() + 1)));
                }
                if (!stackOfTags.empty()) {
                    if (!tag.isClosingTag()) {
                        stackOfTags.push(tag);
                        index = tag.getTagEndIndex();
                        if (index == line.length() - 1 && !isValidContentFound) {
                            System.out.println("None");
                        }
                    } else {
                        if (stackOfTags.peek().getTagName().equals(tag.getTagName())) {
                            if (stackOfTags.peek().isClosingTag() != tag.isClosingTag()) {
                                if (contentMap.get(stackOfTags.peek().getTagId()) != null) {
                                    if (!stackOfTags.peek().getTagName().equals("")) {
                                        isValidContentFound = true;
                                        System.out.println(contentMap.get(stackOfTags.peek().getTagId()));
                                        contentMap.remove(stackOfTags.peek().getTagId());
                                    }
                                }
                                index = tag.getTagEndIndex();
                                if (index == line.length() - 1 && !isValidContentFound) {
                                    System.out.println("None");
                                }
                                stackOfTags.pop();
                            } else {
                                index = stackOfTags.peek().getTagEndIndex() + 1 + tag.getTagStartIndex();
                            }
                        } else if (stackOfTags.peek().isClosingTag() == tag.isClosingTag()) {
                            stackOfTags.push(tag);
                            index = tag.getTagEndIndex();
                        } else {
                            contentMap.remove(stackOfTags.peek().getTagId());
                            index = tag.getTagEndIndex();
                            stackOfTags.pop();
                            if (index == line.length() - 1 && !isValidContentFound) {
                                System.out.println("None");
                            }
                        }
                    }
                } else {
                    stackOfTags.push(tag);
                    index = tag.getTagEndIndex();
                }
            }
            testCases--;
        }
    }


    public static class Tag {
        private String tagName;
        private int tagStartIndex;
        private int TagEndIndex;
        private boolean isClosingTag;
        private String tagId;

        public Tag() {
        }

        public boolean isClosingTag() {
            return isClosingTag;
        }

        public String getTagId() {
            return tagId;
        }

        public void setTagId(String tagId) {
            this.tagId = tagId;
        }

        public void setClosingTag(boolean closingTag) {
            isClosingTag = closingTag;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagStartIndex() {
            return tagStartIndex;
        }

        public void setTagStartIndex(int tagStartIndex) {
            this.tagStartIndex = tagStartIndex;
        }

        public int getTagEndIndex() {
            return TagEndIndex;
        }

        public void setTagEndIndex(int tagEndIndex) {
            TagEndIndex = tagEndIndex;
        }

    }
}



