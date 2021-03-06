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
public class PhoneCrawler extends BreadthCrawler {

	/**
	 * @param crawlPath
	 *            crawlPath is the path of the directory which maintains
	 *            information of this crawler
	 * @param autoParse
	 *            if autoParse is true,BreadthCrawler will auto extract links
	 *            which match regex rules from pag
	 */
	private UrlDAO urlDao = new UrlDAO();
	Timestamp timeMax = null;
	public PhoneCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://news.shouji.com/ceping/");
		this.addRegex("+http://news.shouji.com/ceping/*.*html");
		this.addRegex("-.*\\.(jpg|png|gif).*");
		this.addRegex("-.*sub.*");
		this.addRegex("-.*list.*");
	try{
			timeMax = urlDao.queryMaxTime("news.shouji.com");}
			catch (SQLException e) {
				e.printStackTrace();
		}
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("http://news.shouji.com/ceping/*5.*html")) {
			/* we use jsoup to parse page */
			// Document doc = page.doc();

			/* extract title and content of news by css selector */
			String title = page.select("*[class=content]>h1").text();
			String time = page.select("[class=pubdate]").text();
			String para = page.select("[class=body fs14]>p").text();
			String paras = para.substring(0, 200);
			// String content = page.select("div#Jwrap>h1", 0).text();
			String times = time+":00";
			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("time:\n" + times);
			System.out.println("para:\n" + paras);
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			try {
				date = sdf.parse(times);
				String datef = sdf1.format(date);
				date = sdf1.parse(datef);
				Timestamp sqldate = new Timestamp(date.getTime());
				if(sqldate.after(timeMax)){
				urlDao.insertURL(page.url(), title, paras, date);
				System.out.println("date:\n" + date);
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println("content:\n" + content);

			/* If you want to add urls to crawl,add them to nextLink */
			/*
			 * WebCollector automatically filters links that have been fetched
			 * before
			 */
			/*
			 * If autoParse is true and the link you add to nextLinks does not
			 * match the regex rules,the link will also been filtered.
			 */
			// next.add("http://xxxxxx.com");
		}
	}

	public static void main(String[] args) throws Exception {
		PhoneCrawler crawler = new PhoneCrawler("crawl", true);
		crawler.setThreads(100);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
