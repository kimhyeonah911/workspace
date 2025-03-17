package z.practice.set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class LotteryController {
	private HashSet lottery = new HashSet();
	private HashSet win = new HashSet();
	
	public boolean insertObject(Lottery l) {
		return lottery.add(l);
	}
	
	public boolean deleteObject(Lottery l) {
		/*for(Object exist : lottery) {
			if(((Lottery)exist).getName().equals(l.getName())&&((Lottery)exist).getPhone().equals(l.getPhone())) {
				lottery.remove(l);
				return true;
			}
		}*/
		boolean isRemove = lottery.remove(l);
		if(isRemove && win!=null) {
			win.remove(l);
		}
		return isRemove;
	}
	
	public HashSet winObject(){
		List<Lottery> lotteryList = new ArrayList<>();
		lotteryList.addAll(lottery);
		if(lottery.size()<4) {
			return null;
		}
		while(win.size()<4){
			int random = (int) (Math.random()*lottery.size());
			Lottery select = lotteryList.get(random);
			win.add(select);
		}
		return win;
	}
	
	public TreeSet sortedWinObject() {
		TreeSet sortTree = new TreeSet(new SortedLottery());
		/*TreeSet sortTree = new TreeSet((Object o1, Object o2) -> {
			if(((Lottery)o1).getName().compareTo(((Lottery)o2).getName())==0) {
				return ((Lottery)o1).getPhone().compareTo(((Lottery)o2).getPhone());
			}
			
			//0보다 작은 값 : o1<o2, 0 : o1=o2, 0보다 큰 수 : o1>o2  =>  오름차순
			return ((Lottery)o1).getName().compareTo(((Lottery)o2).getName());
		});*/
		sortTree.addAll(win);
		return sortTree;
	}
	
	public boolean searchWinner(Lottery l) {
		return win.contains(l);
	}
}
