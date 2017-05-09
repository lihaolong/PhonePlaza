package urls;

import java.sql.SQLException;

import DAO.ZealerUrlDAO;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class ZealerCrawler extends BreadthCrawler {

	public ZealerCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://www.zealer.com/media?cid=1&index=0");
		this.addRegex("+http://www.zealer.com/post/*.*html");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {

		/* if page is news page */
		if (page.matchUrl("http://www.zealer.com/post/*.*html")) {

			/* extract title and content of news by css selector */
			String title = page.select("title").text();
			StringBuffer urlSB = new StringBuffer(page.url());
			String postIdstr = urlSB.substring(27, 30); 

			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("postId:\n" + postIdstr);
			
			int postId = Integer.parseInt(postIdstr);
			
			ZealerUrlDAO zealerUrlDAO = new ZealerUrlDAO();
			int maxId = 0;
			try {
				maxId = zealerUrlDAO.queryMaxId();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//只有大于postid才插入数据库
			if(postId>maxId){
			try {
				zealerUrlDAO.insert(postId, page.url(), title);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ZealerCrawler crawler = new ZealerCrawler("crawl", true);
		crawler.setThreads(200);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
