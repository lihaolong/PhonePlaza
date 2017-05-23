package urls;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.FviewUrlDAO;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class FviewCrawler extends BreadthCrawler {
	private FviewUrlDAO urlDao = new FviewUrlDAO();
	Timestamp timeMax = null;

	public FviewCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://v.qq.com/vplus/fview/videos");
		this.addRegex("+http://v.qq.com/page/*.*html");
		try {
			timeMax = urlDao.queryMax();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("http://v.qq.com/page/*.*html")) {

			/* extract title and content of news by css selector */
			String title = page.select("title").text();
			String time = page.select("*[class=date]").text();
			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);

			String times = time.substring(0, 11);

			System.out.println("time:\n" + times);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’");
			Date date;
			try {
				date = sdf.parse(times);
				Timestamp sqldate = new Timestamp(date.getTime());
				if (sqldate.after(timeMax)) {
					urlDao.insert(page.url(), title, sqldate);
					System.out.println("date:\n" + sqldate);
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		FviewCrawler crawler = new FviewCrawler("crawl", true);
		crawler.setThreads(100);
		crawler.setTopN(100);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
