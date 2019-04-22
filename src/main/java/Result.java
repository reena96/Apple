
public class Result {
	int search_time;
	int total_dropped;
	int wait_time;
	int global_time;

	public Result(int global_time){
		this.search_time = 0;
		this.total_dropped = 0;
		this.wait_time = 0;
		this.global_time = global_time;
	}
}
