package urls;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.UrlDAO;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class CnmoCrawler extends BreadthCrawler {
	private UrlDAO urlDao = new UrlDAO();
	Timestamp timeMax = null;

	public CnmoCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://www.cnmo.com/reviews/");
		this.addRegex("+http://www.cnmo.com/reviews/*.*html");
		this.addRegex("-.*\\.(jpg|png|gif).*");
		this.addRegex("-.*list.*");
		this.addRegex("-.*all.*");
		this.addRegex("-.*system.*");
		try {
			timeMax = urlDao.queryMaxTime("www.cnmo.com");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("http://www.cnmo.com/reviews/*.*html")) {

			/* extract title and content of news by css selector */
			String title = page.select("*[class=ctitle]>h1", 0).text();
			String time = page.select("*[class=text_auther]+span").text();
			String para = page.select("*[class=ctext]>p", 0).text();
			// String content = page.select("div#Jwrap>h1", 0).text();
			String times = time + ":00";
			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("time:\n" + times);
			System.out.println("para:\n" + para);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			try {
				date = sdf.parse(times);
				Timestamp sqldate = new Timestamp(date.getTime());
				if (sqldate.after(timeMax)) {
					urlDao.insertURL(page.url(), title, para, date);
					System.out.println("date:\n" + date);
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		CnmoCrawler crawler = new CnmoCrawler("crawl", true);
		crawler.setThreads(100);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
