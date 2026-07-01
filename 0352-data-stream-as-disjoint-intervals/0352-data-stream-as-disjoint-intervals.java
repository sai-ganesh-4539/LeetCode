class SummaryRanges {
    TreeMap<Integer, Integer> x;
    public SummaryRanges() {
        x = new TreeMap<>();
    }
    
    public void addNum(int value) {
        Integer l = x.floorKey(value);
        Integer r = x.ceilingKey(value);
        if (l != null && x.get(l) >= value) {
            return;
        }
        if (l != null && r != null && x.get(l) + 1 == value && r == value + 1) {
            x.put(l, x.get(r));
            x.remove(r);
        } else if (l != null && x.get(l) + 1 >= value) {
            x.put(l, Math.max(x.get(l), value));
        } else if (r != null && r == value + 1) {
            x.put(value, x.get(r));
            x.remove(r);
        } else {
            x.put(value, value);
        }
    }
    public int[][] getIntervals() {
        int[][] ans = new int[x.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : x.entrySet()) {
            ans[i][0] = e.getKey();
            ans[i][1] = e.getValue();
            i++;
        }
        return ans;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */