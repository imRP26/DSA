import java.util..*;

/*
 * https://leetcode.com/problems/subdomain-visit-count/
 */



// My Naive Solution
class Solution1 {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domainCount = new HashMap<>();
        for (String str : cpdomains) {
            String num = "";
            for (char c : str.toCharArray()) {
                if (c == ' ')
                    break;
                num += c;
            }
            int count = Integer.parseInt(num);
            String domain = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                char c = str.charAt(i);
                if (c == ' ')
                    break;
                if (c == '.')
                    domainCount.put(domain, domainCount.getOrDefault(domain, 0) + count);
                domain = c + domain;
            }
            domainCount.put(domain, domainCount.getOrDefault(domain, 0) + count);
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            String domCount = entry.getValue() + " " + entry.getKey();
            result.add(domCount);
        }
        return result;
    }
}



// A bit better, but using the same approach as above
class Solution2 {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domainCount = new HashMap<>();
        for (String str : cpdomains) {
            StringBuilder num = new StringBuilder();
            for (char c : str.toCharArray()) {
                if (c == ' ')
                    break;
                num.append(c);
            }
            int count = Integer.parseInt(num.toString());
            StringBuilder domain = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                char c = str.charAt(i);
                if (c == ' ')
                    break;
                if (c == '.')
                    domainCount.put(domain.toString(), domainCount.getOrDefault(domain.toString(), 0) + count);
                domain.insert(0, c);
            }
            domainCount.put(domain.toString(), domainCount.getOrDefault(domain.toString(), 0) + count);
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            String domCount = entry.getValue() + " " + entry.getKey();
            result.add(domCount);
        }
        return result;
    }
}
