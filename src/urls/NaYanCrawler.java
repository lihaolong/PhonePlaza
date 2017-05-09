package urls;

import java.sql.SQLException;

import DAO.NaYanUrlDAO;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class NaYanCrawler extends BreadthCrawler {


	public NaYanCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://www.kjmx.com/category/video");
		this.addRegex("+http://www.kjmx.com/post/*.*");
		this.addRegex("-.*#.*");
		this.addRegex("-.*date.*");
		this.addRegex("-.*author.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {

		/* if page is news page */
		if (page.matchUrl("http://www.kjmx.com/post/*.*")) {

			/* extract title and content of news by css selector */
			String title = page.select("title").text();
			StringBuffer urlSB = new StringBuffer(page.url());
			String postIdstr = urlSB.substring(25, 29); 

			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("postId:\n" + postIdstr);
			
			int postId = Integer.parseInt(postIdstr);
			
			NaYanUrlDAO naYanUrlDAO = new NaYanUrlDAO();
			int maxId = 0;
			try {
				maxId = naYanUrlDAO.queryMaxId();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(postId>maxId){
			try {
				naYanUrlDAO.insert(postId, page.url(), title);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		NaYanCrawler crawler = new NaYanCrawler("crawl", true);
		crawler.setThreads(200);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
