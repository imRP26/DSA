import java.util.*;


/*
 * Fantastic TCs - my naive solution - pretty much self-explanatory!!
 */
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String folder = "", result = "";
        for (char c : path.toCharArray()) {
            if (c == '/') {
                if (folder.equals("..") || folder.equals(".")) {
                    if (!stack.empty() && folder.equals(".."))
                        stack.pop();
                    folder = "";
                    continue;
                }
                if (folder != "")
                    stack.push(folder);
                folder = "";
            }
            else
                folder += c;
        }
        if (folder.equals("..") && !stack.empty())
            stack.pop();
        else if (folder != "" && !folder.equals(".") && !folder.equals(".."))
            stack.push(folder);
        while (!stack.empty()) {
            result = '/' + stack.peek() + result;
            stack.pop();
        }
        if (result == "")
            result = '/' + result;
        return result;
    }
}
