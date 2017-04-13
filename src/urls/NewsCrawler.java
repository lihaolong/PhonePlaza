package urls;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.urlDAO;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * Crawling news from hfut news
 *
 * @author hu
 */
public class NewsCrawler extends BreadthCrawler {

	/**
	 * @param crawlPath
	 *            crawlPath is the path of the directory which maintains
	 *            information of this crawler
	 * @param autoParse
	 *            if autoParse is true,BreadthCrawler will auto extract links
	 *            which match regex rules from pag
	 */
	public NewsCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		/* start page */
		this.addSeed("http://mobile.pconline.com.cn/review/");

		/* fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml */
		this.addRegex("+http://mobile.pconline.com.cn/*.*html");
		/* do not fetch jpg|png|gif */
		this.addRegex("-.*\\.(jpg|png|gif).*");
		/* do not fetch url contains # */
		this.addRegex("-.*review.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("http://mobile.pconline.com.cn/*.*html")) {
			/* we use jsoup to parse page */
			// Document doc = page.doc();

			/* extract title and content of news by css selector */
			String title = page.select("*[class=art-hd]>h1", 0).text();
			String time = page.select("[class=pubtime]").text();
			String para = page.select("[class=content]>table>tbody>tr>td>p", 0).text();
			// String content = page.select("div#Jwrap>h1", 0).text();
			String times = time+":00";
			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("time:\n" + times);
			System.out.println("para:\n" + para);
			


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date;
			urlDAO urlDao = new urlDAO();
			try {
				date = sdf.parse(times);
				urlDao.insertURL(page.url(), title, para, date);
				System.out.println("date:\n" + date);
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
		NewsCrawler crawler = new NewsCrawler("crawl", true);
		crawler.setThreads(100);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
