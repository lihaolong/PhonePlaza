package urls;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DAO.VideoUrlDAO;
import DAO.urlDAO;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class DaMiCrawler extends BreadthCrawler {

	/**
	 * @param crawlPath
	 *            crawlPath is the path of the directory which maintains
	 *            information of this crawler
	 * @param autoParse
	 *            if autoParse is true,BreadthCrawler will auto extract links
	 *            which match regex rules from pag
	 */
	private VideoUrlDAO videoUrlDAO = new VideoUrlDAO();
	Timestamp timeMax = null;

	public DaMiCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://www.pingce.net/pingce");
		this.addRegex("+http://www.pingce.net/*.*html");
		/*
		 * try{ timeMax = urlDao.queryMaxTime("mobile.pconline.com.cn");} catch
		 * (SQLException e) { e.printStackTrace(); }
		 */
	}

	@Override
	public void visit(Page page, CrawlDatums next) {

		/* if page is news page */
		if (page.matchUrl("http://www.pingce.net/*.*html")) {

			/* extract title and content of news by css selector */
			String title = page.select("*[class=article_container row  box]>h1").text();
			String time = page.select("[class=info_date info_ico]").text();
			StringBuffer sbTime = new StringBuffer(time);
			if(Integer.parseInt(sbTime.substring(0, 2).toString())>10){
			sbTime.insert(0, "2016-");
			}else{
				sbTime.insert(0, "2017-");
			}

			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("time:\n" + time);


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = new Date(sdf.parse(sbTime.toString()).getTime());
				if(date.after(videoUrlDAO.queryMax())){
				videoUrlDAO.insert(page.url(), title, date);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		DaMiCrawler crawler = new DaMiCrawler("crawl", true);
		crawler.setThreads(200);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
