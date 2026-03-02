package com.epam.practice;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.epam.practice.dto.Employee;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
		System.out.println("Hi");

		//object related 
		List<Employee> empList = new ArrayList<>();

		empList.add(new Employee(1, "Yanksha", 28, 123, "F","yanksha@gmail.com", "HR", "Blore", 2020));

		empList.add(new Employee(2, "Francesca", 29, 120, "F","francesca@yahoo.com", "HR", "Hyderabad", 2035));

		empList.add(new Employee(3, "Ramesh", 30, 115, "M","ramesh@gmail.com", "HR", "Chennai", 2014));

		empList.add(new Employee(4, "Melanie", 32, 160, "F","melanie@outlook.com", "HR", "Chennai", 2013));

		empList.add(new Employee(5, "Padma", 22, 150, "F","padma@gmail.com", "IT", "Noida", 2013));

		empList.add(new Employee(6, "Milad", 27, 140, "M","milad@yahoo.com", "IT", "Gurugram", 2017));

		empList.add(new Employee(7, "Uzma", 26, 130, "F","uzma@gmail.com", "IT", "Pune", 2016));

		empList.add(new Employee(8, "Ali", 23, 145, "M","ali@outlook.com", "IT", "Trivandam", 2015));

		empList.add(new Employee(9, "Ram", 25, 160, "M","ram@gmail.com", "IT", "Blore", 2010));

		empList.add(new Employee(10, "Shiva", 27, 147, "M","shiva@yahoo.com", "FINANCE", "Hyderabad", 2021));


		//Map<String,List<Employee>> city = empList.stream().collect(Collectors.groupingBy(Employee::getCity)); //output: Chennai = [Employee object] 
		Map<String,List<String>> city= empList.stream().collect(Collectors.groupingBy(Employee::getCity,
				Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println("city "+city); // {Chennai=[Ramesh, Melanie], Trivandam=[Ali], Pune=[Uzma], Noida=[Padma], Blore=[Yanksha, Ram], Gurugram=[Milad], Hyderabad=[Francesca, Shiva]}


		Map<String, Long> genderCount = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println("genderCount "+genderCount); // genderCount {F=5, M=5}
		Map<String,Map<String,Long>> cityGenderCount= empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
				Collectors.groupingBy(Employee::getGender, Collectors.counting())));
		System.out.println("cityGenderCount "+cityGenderCount); //cityGenderCount {FINANCE={M=1}, HR={F=3, M=1}, IT={F=2, M=3}}

		int older = empList.stream().map(Employee::getYearOfJoining).sorted(Comparator.reverseOrder()).findFirst().orElse(-1);
		System.out.println("older "+older); //2035
		int maxAge =
				empList.stream()
				.mapToInt(Employee::getAge)
				.max()
				.orElse(0); //32

		Map<String, Double> avgAgeOfGender = empList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println("avgAgeOfGender "+avgAgeOfGender); // {F=27.4, M=26.4}

		Map<String,Map<String, Double>> avgAgeOfGenOfDep=empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));
		System.out.println("avgAgeOfGenOfDep "+avgAgeOfGenOfDep);//{FINANCE={M=27.0}, HR={F=29.666666666666668, M=30.0}, IT={F=24.0, M=25.0}}
		int oldestServe = empList.stream().mapToInt(Employee::getYearOfJoining).min().orElse(0);
		System.out.println("oldestServe "+oldestServe);

		Map<Boolean,List<Employee>> agedivison = empList.stream().collect(Collectors.partitioningBy(e->e.getAge()>30));
		System.out.println("agedivison "+agedivison);//{false=[Employee [id=1, name=Yanksha, age=28, salary=123, gender=F, deptName=HR, city=Blore, yearOfJoining=2020], Employee [id=2, name=Francesca, age=29, salary=120, gender=F, deptName=HR, city=Hyderabad, yearOfJoining=2035], Employee [id=3, name=Ramesh, age=30, salary=115, gender=M, deptName=HR, city=Chennai, yearOfJoining=2014], Employee [id=5, name=Padma, age=22, salary=150, gender=F, deptName=IT, city=Noida, yearOfJoining=2013], Employee [id=6, name=Milad, age=27, salary=140, gender=M, deptName=IT, city=Gurugram, yearOfJoining=2017], Employee [id=7, name=Uzma, age=26, salary=130, gender=F, deptName=IT, city=Pune, yearOfJoining=2016], Employee [id=8, name=Ali, age=23, salary=145, gender=M, deptName=IT, city=Trivandam, yearOfJoining=2015], Employee [id=9, name=Ram, age=25, salary=160, gender=M, deptName=IT, city=Blore, yearOfJoining=2010], Employee [id=10, name=Shiva, age=27, salary=147, gender=M, deptName=FINANCE, city=Hyderabad, yearOfJoining=2021]], true=[Employee [id=4, name=Melanie, age=32, salary=160, gender=F, deptName=HR, city=Chennai, yearOfJoining=2013]]}

		Employee em = empList.stream().filter(e->e.getDeptName().equals("HR")).findAny().orElse(null);
		System.out.println("Hr dep "+ em.toString()); //[id=1, name=Yanksha, age=28, salary=123, gender=F, deptName=HR, city=Blore, yearOfJoining=2020]

		List<String> Depgreater3= empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).entrySet().stream().filter(e->e.getValue()>3).map(Map.Entry::getKey).collect(Collectors.toList());
		System.out.println("Dep greater than 3"+ Depgreater3);   // [HR, IT]


		List<Employee> sortAgeAndName = empList.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getName)).toList();
		System.out.println("sortAgeAndName"+ sortAgeAndName); //sortAgeAndName[Employee [id=5, name=Padma, age=22, salary=150, gender=F, deptName=IT, city=Noida, yearOfJoining=2013], Employee [id=8, name=Ali, age=23, salary=145, gender=M, deptName=IT, city=Trivandam, yearOfJoining=2015], Employee [id=9, name=Ram, age=25, salary=160, gender=M, deptName=IT, city=Blore, yearOfJoining=2010], Employee [id=7, name=Uzma, age=26, salary=130, gender=F, deptName=IT, city=Pune, yearOfJoining=2016], Employee [id=6, name=Milad, age=27, salary=140, gender=M, deptName=IT, city=Gurugram, yearOfJoining=2017], Employee [id=10, name=Shiva, age=27, salary=147, gender=M, deptName=FINANCE, city=Hyderabad, yearOfJoining=2021], Employee [id=1, name=Yanksha, age=28, salary=123, gender=F, deptName=HR, city=Blore, yearOfJoining=2020], Employee [id=2, name=Francesca, age=29, salary=120, gender=F, deptName=HR, city=Hyderabad, yearOfJoining=2035], Employee [id=3, name=Ramesh, age=30, salary=115, gender=M, deptName=HR, city=Chennai, yearOfJoining=2014], Employee [id=4, name=Melanie, age=32, salary=160, gender=F, deptName=HR, city=Chennai, yearOfJoining=2013]]

		Map<String, Double> avgSalaryofDept = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingLong(Employee::getSalary)));
		System.out.println("avgSalaryofDept "+avgSalaryofDept); //  {FINANCE=147.0, HR=129.5, IT=145.0}
		List<Employee> empgravgsalary =empList.stream().filter(e-> e.getSalary()> avgSalaryofDept.get(e.getDeptName()) ).collect(Collectors.toList());
		System.out.println("empgravgsalary "+empgravgsalary); //  [Employee [id=4, name=Melanie, age=32, salary=160, gender=F, deptName=HR, city=Chennai, yearOfJoining=2013], Employee [id=5, name=Padma, age=22, salary=150, gender=F, deptName=IT, city=Noida, yearOfJoining=2013], Employee [id=9, name=Ram, age=25, salary=160, gender=M, deptName=IT, city=Blore, yearOfJoining=2010]]

		Long highestSalary = empList.stream().mapToLong(Employee::getSalary).max().orElse(0L);
		System.out.println("highestSalary "+highestSalary); //  160

		Long secondHighestSalary = empList.stream().map(Employee::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0L);
		System.out.println("secondHighestSalary "+secondHighestSalary); //  150

		Map<String,Optional<Employee>> maxSalaryGender =empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
		System.out.println("maxSalaryGender "+maxSalaryGender); // {F=Optional[Employee [id=4, name=Melanie, age=32, salary=160, gender=F, deptName=HR, city=Chennai, yearOfJoining=2013]], M=Optional[Employee [id=9, name=Ram, age=25, salary=160, gender=M, deptName=IT, city=Blore, yearOfJoining=2010]]}

		List<Employee> sortsalaryandAge = empList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getAge)).toList();
		System.out.println("sortsalaryandAge"+ sortsalaryandAge);// [Employee [id=9, name=Ram, age=25, salary=160, gender=M, deptName=IT, city=Blore, yearOfJoining=2010], Employee [id=4, name=Melanie, age=32, salary=160, gender=F, deptName=HR, city=Chennai, yearOfJoining=2013], Employee [id=5, name=Padma, age=22, salary=150, gender=F, deptName=IT, city=Noida, yearOfJoining=2013], Employee [id=10, name=Shiva, age=27, salary=147, gender=M, deptName=FINANCE, city=Hyderabad, yearOfJoining=2021], Employee [id=8, name=Ali, age=23, salary=145, gender=M, deptName=IT, city=Trivandam, yearOfJoining=2015], Employee [id=6, name=Milad, age=27, salary=140, gender=M, deptName=IT, city=Gurugram, yearOfJoining=2017], Employee [id=7, name=Uzma, age=26, salary=130, gender=F, deptName=IT, city=Pune, yearOfJoining=2016], Employee [id=1, name=Yanksha, age=28, salary=123, gender=F, deptName=HR, city=Blore, yearOfJoining=2020], Employee [id=2, name=Francesca, age=29, salary=120, gender=F, deptName=HR, city=Hyderabad, yearOfJoining=2035], Employee [id=3, name=Ramesh, age=30, salary=115, gender=M, deptName=HR, city=Chennai, yearOfJoining=2014]]

		Map<String,Long> GroupEmailCount = empList.stream().map(Employee::getEmail).map(st->st.substring(st.indexOf('@')+1)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		System.out.println("Group emails "+ GroupEmailCount);//Group emails {outlook.com=2, gmail.com=5, yahoo.com=3}
		
		Map<String,List<String>> GroupEmailNames = empList.stream().collect(Collectors.groupingBy(emp -> emp.getEmail().substring(emp.getEmail().indexOf('@')+1),Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println("Group emails names "+ GroupEmailNames); //Group emails {outlook.com=[Melanie, Ali], gmail.com=[Yanksha, Ramesh, Padma, Uzma, Ram], yahoo.com=[Francesca, Milad, Shiva]}
		
		
		
		
		//string related
		String str="Ramya";
		String sentence = "Ramya is a good girl but not a good girlfriend";
		String[] splitSentence = sentence.trim().split("\\s+"); // for extra spaces like "Ramya    is good   girl" -- Ramya,sri,lakshmi,sunkara
		
		String distinct= str.chars().mapToObj(c->(char)c).distinct().map(String::valueOf).collect(Collectors.joining());
		System.out.println("distinct string "+ distinct); //Ramy
		Map<Character,Long> charCount = str.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(" charCount "+ charCount);// {a=2, R=1, y=1, m=1}
		Map<String, Long> wordCount= Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(" sentence "+ wordCount);//{but=1, girlfriend=1, a=2, not=1, Ramya=1, is=1, girl=1, good=2}

		Character repeatChar = str.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting())).entrySet().stream().filter(e->e.getValue()>1).map(Map.Entry::getKey).findFirst().orElse(null);
		System.out.println(" repeatChar "+ repeatChar);//a
		String longWord= Arrays.stream(sentence.split(" ")).max(Comparator.comparingInt(String::length)).orElse("No word");
		System.out.println(" longWord "+ longWord);//girlfriend
 
		
		String secondLongWord = Arrays.stream(sentence.split(" ")).sorted(Comparator.comparingInt(String::length).reversed()).skip(1).findFirst().orElse("Not Found");
		System.out.println(" secondLongWord "+ secondLongWord);//Ramya
		Map<String, Long> vowelCountOfEachWord = Arrays.stream(sentence.split(" ")).distinct().collect(Collectors.toMap(Function.identity(), word-> word.chars().filter(c-> "aeiou".indexOf(c)!= -1).count()));
		System.out.println("vowelCountOfEachWord "+vowelCountOfEachWord);
		Collections.max(vowelCountOfEachWord.values()); //to find max of vowel count in a sentence
		String strWithSplChars = "Ra$my!a";
		int left =0,right=strWithSplChars.length()-1;
		char[] c = strWithSplChars.toCharArray();
		while(left<right) {
			if(!Character.isLetter(c[left])) {
				left++;
			}
			else if(!Character.isLetter(c[right])) {
				right--;
			}
			else  {
				char temp= c[left] ;
				c[right--]= c[left];
				c[left++]=temp;
			}
		}
		
		System.out.println("strWithSplChars "+ new String(c));
		
		List<String> start = Stream.of("apple","banana","apricot").distinct().filter(s->s.startsWith("a")).collect(Collectors.toList());
		System.out.println(" start "+ start);// [apple, apricot]

		
		//String [] Array

		String[] strArr = new String[] {"40","9","23","89","60"}; 
		String longestNumber = Arrays.stream(strArr).sorted((a,b)->(b+a).compareTo(a+b)).collect(Collectors.joining());
		System.out.println("Longest Number "+ longestNumber);//989604023  -- compare adjacent numbers by appending front and back like -- ex 40 and 9 then 409.compareTo 940 then we choose 940

		//String[] strArrWords = {"flower","flowre","flowing",};
		String[] strArrWords = {"ramya","sri","lakshmi","Ramya","sri"}; //count prefix of ram
		int count =0;
		String prefix = strArrWords[0];
		for(int i=1;i<strArrWords.length;i++) {
			while(!strArrWords[i].startsWith(prefix)) {
				prefix = prefix.substring(0,prefix.length()-1);
			}
			if(strArrWords[i].startsWith("sri"))
				count++;
		}
		System.out.println("prefix "+prefix); //flow
		System.out.println("count of prefix same "+count); //2
		/* Map<String, List<String>> map = new HashMap<>();

    for (String word : words) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);

        String key = new String(chars); // ✅ THIS is the check

        map.computeIfAbsent(key, k -> new ArrayList<>())
           .add(word);
    }

    return new ArrayList<>(map.values());*/ //[[flower, flowre], [flowing]]

		List<List<String>> anagrams = Arrays.stream(strArrWords).collect(Collectors.groupingBy(word->{
			char[] ch =word.toCharArray();
			Arrays.sort(ch);
			return new String(ch);
		})).values().stream().toList();

		System.out.println("anagrams "+ anagrams);  //[[flower, flowre], [flowing]]

		String[] words = {"ramya","sri","lakshmi","Ramya","sri"};
		
		
		List<String> wordsBasedOnLength = Arrays.stream(words).sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
		System.out.println("wordsBasedOnLength "+ wordsBasedOnLength); //wordsBasedOnLength  [sri, sri, ramya, Ramya, lakshmi]

		List<String> wordsBasedOnLex= Arrays.stream(words).sorted().collect(Collectors.toList());
		System.out.println("wordsBasedOnLex "+ wordsBasedOnLex); //wordsBasedOnLex [Ramya, lakshmi, ramya, sri, sri] captial letter comes first

		List<String> distinctwords = Arrays.stream(words).map(String::toLowerCase).distinct().collect(Collectors.toList());
		System.out.println("distinctwords "+ distinctwords); //[ramya, sri, lakshmi]

		String maxWord = Arrays.stream(words).max(Comparator.comparing(String::length)).map(String::valueOf).orElse("");
		System.out.println("Max word "+maxWord); //lakshmi

		String concatAndReverse = IntStream.range(0, words.length).mapToObj(w-> words[words.length-1-w]).collect(Collectors.joining(" "));
		System.out.println("concatAndReverse "+concatAndReverse); //sri Ramya lakshmi sri ramya

		StringBuilder sb = new StringBuilder();

		for (int i = words.length - 1; i >= 0; i--) {
			sb.append(words[i]);
			if (i != 0) sb.append(" ");
		}

		System.out.println(sb.toString()); //sri Ramya lakshmi sri ramya

		String[] alphaNumbers = {"ae","123","we","234","768"};
		//List<Integer> onlyNumbers= Arrays.stream(alphaNumbers).filter(s->s.matches("\\d+")).map(Integer::valueOf).toList();  // old style
		List<Integer> onlyNumbers= Arrays.stream(alphaNumbers).filter(s->s.chars().allMatch(Character::isDigit)).map(Integer::valueOf).toList();
		
		System.out.println("Only numbers" +onlyNumbers);
		
		
		  String[][] scores = {
			        {"Bobby", "87"},
			        {"Charles", "100"},
			        {"Eric", "64"},
			        {"Charles", "22"},
			        {"Bobby", "94"},
			        {"Eric", "-10"},
			        {"David", "50"},
			        {"David", "70"}
			};
		
		Map<String , Double> avgScore = Arrays.stream(scores).collect(Collectors.groupingBy(score -> score[0], Collectors.averagingInt(score -> Integer.parseInt(score[1]))));
		System.out.println("avg score :: "+avgScore);
		int maxAvgScore = avgScore.values().stream().max(Double::compareTo).map(s-> (int)Math.floor(s)).orElse(0);
		System.out.println(" max avg score :: "+maxAvgScore);
		String[] logs = {
			    "192.168.1.10 - - [10/Jan/2026:10:01:10 +0530] \"GET /home HTTP/1.1\" 200",
			    "192.168.1.11 - - [10/Jan/2026:10:01:20 +0530] \"GET /login HTTP/1.1\" 200",
			    "192.168.1.10 - - [10/Jan/2026:10:02:10 +0530] \"GET /profile HTTP/1.1\" 200",
			    "192.168.1.12 - - [10/Jan/2026:10:03:10 +0530] \"GET /about HTTP/1.1\" 200"
			};
		
		Arrays.stream(logs).findFirst().map(s-> s.substring(0, 13)).toString();
		
		
		
		
		
		
		//int[] related issues

		int[] nums = new int[] {1 ,2,0,3, 4,0, 5, 6,0};
		int rotate = 4;
		int[] temp = new int[rotate];

		for(int i=0;i<rotate;i++) {
			temp[i]=nums[i];
		}

		for(int i = rotate; i<nums.length;i++) {
			nums[i-rotate]=nums[i];
		}
		for(int i = 0;i<rotate;i++) {
			nums[nums.length-rotate+i]=temp[i];
		}

		System.out.println("rotate the elements "+Arrays.toString(nums)); //[4, 0, 5, 6, 0, 1, 2, 0, 3]

		int[] zeroLast = new int[nums.length]; int pointer =0;
		for(int num: nums) {
			if(num!=0)
				zeroLast[pointer++] = num;

		}
		while(pointer<nums.length) {
			zeroLast[pointer++]= 0;
		}
		System.out.println("Zeros last "+ Arrays.toString(zeroLast)); //[4, 5, 6, 1, 2, 3, 0, 0, 0]

		int[] result =
				IntStream.concat(
						Arrays.stream(nums).filter(n -> n != 0),
						Arrays.stream(nums).filter(n -> n == 0)
						).toArray();// same using streams  -zeros last
		int[] numbers = {90,78,56,52,28,92,25,29,54,77};
		Map<Integer,List<Integer>> rangeGroup= Arrays.stream(numbers).boxed().collect(Collectors.groupingBy(num -> (num/10)*10));
		System.out.println("Range groupig "+ rangeGroup);
		int[] duplicateNums = {23,6,3,34,5,2,6,24,8,26};
		int distinctSum = Arrays.stream(duplicateNums).distinct().sum();
		System.out.println("distinct numbers sum "+ distinctSum);
		
		String minPossibleNum =Arrays.stream(duplicateNums).mapToObj(String::valueOf).sorted((a,b)-> (a+b).compareTo(b+a)).collect(Collectors.joining());
		System.out.println("min possible Num "+minPossibleNum);
		
		String maxPossibleNum =Arrays.stream(duplicateNums).mapToObj(String::valueOf).sorted((a,b)-> (b+a).compareTo(a+b)).collect(Collectors.joining());
		System.out.println("max possible Num "+maxPossibleNum);
		
		
		
		//list stream
		
		List<String> listOfWords = Arrays.asList("ramya","sri","lakshmi");
		List<String> captialised = listOfWords.stream().map(word->word.substring(0,1).toUpperCase()+word.substring(1)).distinct().toList();
		System.out.println("captialised "+ captialised);
		List<Integer> listNums = Arrays.asList(2,4,23,4,7,4,8);
		Predicate<Integer> isEven = num -> num%2==0;
		Function<Integer,Integer> prod = num -> num*num;
		List<Integer> squareofEven = listNums.stream().filter(isEven).map(prod).toList();
		System.out.println("Squares of even number "+ squareofEven);
		
		
		
		
	}
	
	public static int trap(int[] height) {  // water trap problem

	    if (height == null || height.length == 0) {
	        return 0;
	    }

	    int left = 0;
	    int right = height.length - 1;
	    int leftMax = 0;
	    int rightMax = 0;
	    int water = 0;

	    while (left < right) {

	        if (height[left] < height[right]) {

	            if (height[left] >= leftMax) {
	                leftMax = height[left];
	            } else {
	                water += leftMax - height[left];
	            }

	            left++;

	        } else {

	            if (height[right] >= rightMax) {
	                rightMax = height[right];
	            } else {
	                water += rightMax - height[right];
	            }

	            right--;
	        }
	    }

	    return water;
	}
	
	
	public static int maximumGap(int[] nums) {
	    if (nums == null || nums.length < 2) {
	        return 0;
	    }

	    Arrays.sort(nums);

	    int maxGap = 0;

	    for (int i = 1; i < nums.length; i++) {
	        maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
	    }

	    return maxGap;
	}
	
	
	public static void nextHighestNumber() {

        int[] arr = {15, 10, 16, 20, 8, 9, 7, 50};

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> result = new HashMap<>();

        for (int num : arr) {

            while (!stack.isEmpty() && num > stack.peek()) {
                result.put(stack.pop(), num);
            }

            stack.push(num);
        }

        // Remaining elements have no greater element
        while (!stack.isEmpty()) {
            result.put(stack.pop(), -1);
        }
	}
	
	 public static void secondHighest(){  //second highest
	        
	       int[] arr = {1, 7 ,5,6,9,8,10,24,21,26,28};
	       int highest=Integer.MIN_VALUE, second=Integer.MIN_VALUE;
	        for (int i: arr){
	            if(i>highest){
	                second = highest;
	                highest = i;
	            }
	            else if(i>second && second!=highest){
	                second = i;
	            }
	        }
	       System.out.println("second "+ second);
	    }
	 
	   public static int findMin(int[] nums) {  // minimum number in rotated array follow a binary search

	        int left = 0;
	        int right = nums.length - 1;

	        while (left < right) {

	            int mid = left + (right - left) / 2;

	            // If mid element is greater than right element,
	            // minimum must be in right half
	            if (nums[mid] > nums[right]) {
	                left = mid + 1;
	            }
	            // Otherwise minimum is in left half (including mid)
	            else {
	                right = mid;
	            }
	        }

	        return nums[left];
	    }

	   public static int[] trackPosition(String moves) { // input : "UDLR" output : [0,0]

	        int x = 0;
	        int y = 0;

	        for (char move : moves.toCharArray()) {

	            switch (move) {
	                case 'U':
	                    y++;
	                    break;
	                case 'D':
	                    y--;
	                    break;
	                case 'R':
	                    x++;
	                    break;
	                case 'L':
	                    x--;
	                    break;
	                default:
	                    // ignore invalid characters
	            }
	        }

	        return new int[]{x, y};
	    }


 
}
