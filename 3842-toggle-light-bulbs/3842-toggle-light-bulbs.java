class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        HashSet<Integer> s = new HashSet<>();
        for (int bulb : bulbs) {
            if (s.contains(bulb)) s.remove(bulb);
            else s.add(bulb);
        }
        List<Integer> r = new ArrayList<>(s);
        Collections.sort(r);
        return r;
    }
}