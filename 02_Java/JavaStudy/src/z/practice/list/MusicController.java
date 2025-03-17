package z.practice.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import z.practice.list.Music;

public class MusicController {
	private List list = new ArrayList();
	
	public int addList(Music music) {
		list.add(music);
		return 1;
	}
	
	public int addAtZero(Music music) {
		list.add(0, music);
		return 1;
	}
	
	public List printAll() {
		return list;
	}
	
	public Music searchMusic(String title) {
		for(Object obj : list) {
			Music m = (Music)obj;
			if(m.getTitle().equals(title)) {
				return m;
			}
		}
		return null;
	}
	
	public Music removeMusic(String title) {
		Music m = this.searchMusic(title);
		if(m != null) {
			list.remove(m);
		}
		/*for(Object obj : list) {
			Music m = (Music)obj;
			if(m.getTitle().equals(title)) {
				list.remove(m);
				return m;
			}
		}*/
		return m;
	}
	
	public Music setMusic(String title, Music music) {
		/*Music music1 = new Music();
		for(int i=0; i<list.size(); i++) {
			if(((Music)list).getTitle().equals(title)) {
				music1 = (Music)list.set(i, music);
				return music1;
			}
		}*/
		Music m = searchMusic(title);
		if(m != null) {
			int index = list.indexOf(m);
			list.set(index, music);
		}
		return m;
	}
	
	public int ascTitle() {
		//Collections.sort : 컬렉션에서 정렬 기능을 제공하는 메서드
		//정렬하고 싶은 컬렉션 객체와 정렬 기준을 정한 객체(Comparator 구현한 객체)
		//전달하면 정렬 기분에 맞춰 정렬을 수행해준다.
		Collections.sort(list, new AscTitle());
		return 1;
	}

	public int descSinger() {
		/*Collections.sort(list, new Comparator<Music>() {
			public int compare(Music o1, Music o2) {
				return o2.getSinger().compareTo(o1.getSinger());
			}
		});*/
		
		/*
		 * 함수형 인터페이스
		 * 단 하나의 추상메서드를 가진 인터페이스
		 * 람다식을 통해서 단일추상메서드를 바로 구현해서 사용할 수 있다.
		 */
		
		//람다식의 기본 구조
		// (매개변수) -> {실행할 코드}
		//반환값이 1개일 때는 {} 생략 가능
		// (매개변수) -> 반환값
		Collections.sort(list, (o1, o2) -> ((Music)o2).getSinger().compareTo(((Music)o1).getSinger()));
		return 1;
	}
}
