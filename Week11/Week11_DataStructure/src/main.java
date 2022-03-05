import java.util.Arrays;

/**
 * @Classname : main //类名
 * @Description:  CSA第11周作业  //描述
 * @Author : Administrator //作者
 * @Date : 2022/3/2 15:44//日期
 */
public class main {
    public static void main(String[] args) {
        System.out.println("----------第一题-----------");
        int nums[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement(nums));
        System.out.println("----------第二题-----------");
        int flowerbed[] = {1,0,0,0,1};
        int n = 1;
        System.out.println(canPlaceFlowers(flowerbed,n));
        System.out.println("----------第三题-----------");
        int intervals[][] = {{1,2},{1,2},{1,2}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    /**
     * 1. 寻找数组中出现次数超过一半的数字
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     *
     * 思路:
     * 对每个元素进行计数
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int res=0,count=0;
        for(int i=0;i<nums.length;i++){
            if(count==0){
                res=nums[i];
                count++;
            }else{
                if(nums[i]==res){
                    count++;
                }else{
                    count--;
                }
            }
        }
        return res;
    }

    /**
     * 2. 种花问题
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
     * 可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，
     * 其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？
     * 能则返回 true ，不能则返回 false。
     *
     * 提示：
     * `1 <= flowerbed.length <= 2*10^4`
     * `flowerbed[i] 为 0 或 1`
     * `flowerbed 中不存在相邻的两朵花`
     * `0 <= n <= flowerbed.length`
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 贪心
        int count=0;
        for(int i=0;i<flowerbed.length;i++){
            if(flowerbed[i]==0){
                if(i==0){
                    if(i==flowerbed.length-1){
                        count++;
                        flowerbed[i]=1;
                    }else if(flowerbed[i+1]!=1){
                        count++;
                        flowerbed[i]=1;
                    }
                }else if(i>0&&i<flowerbed.length-1){
                    if(flowerbed[i-1]!=1&&flowerbed[i+1]!=1){
                        count++;
                        flowerbed[i]=1;
                    }
                }else if(i==flowerbed.length-1&&i>0&&flowerbed[i-1]!=1){
                    count++;
                    flowerbed[i]=1;
                }
            }
        }
        return count>=n;
    }

    /**
     * 3. 无重叠区间
     * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。
     * 返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     *
     * 提示：
     * `1 <= intervals.length <=10^5`
     * `intervals[i].length == 2`
     * `-5 * 10^4 <= starti < endi <= 5 * 10^4`
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<2){
            return 0;
        }

        // 根据右边界排序
        Arrays.sort(intervals,(a, b)->{
            return a[1]-b[1];
        });

        int count=1,right=intervals[0][1];
        // 最大无重叠区间的个数
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]>=right){
                count++;
                right=intervals[i][1];
            }
        }

        return intervals.length-count;
    }

}
