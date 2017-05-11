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
public class ZolCrawler extends BreadthCrawler {
	private UrlDAO urlDao = new UrlDAO();
	public ZolCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		this.addSeed("http://mobile.zol.com.cn/more/2_428.shtml");
		this.addRegex("+http://mobile.zol.com.cn/*.*html");
		this.addRegex("-.*\\.(jpg|png|gif).*");
		this.addRegex("-.*more.*");
		this.addRegex("-.*_.*");
		this.addRegex("-.*iphone.*");
		this.addRegex("-.*android.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		/* if page is news page */
		if (page.matchUrl("http://mobile.zol.com.cn/*.*html")) {
			/* we use jsoup to parse page */
			// Document doc = page.doc();

			/* extract title and content of news by css selector */
			String title = page.select("*[class=article-header editor-hasPic clearfix]>h1").text();
			String time = page.select("[id=pubtime_baidu]").text();
			String para = page.select("[class=article-cont clearfix]>p").text();
			//去除空格变成的问号
			String paras = null;
			try {
		        paras = new String(para.getBytes(),"GBK").replace('?', ' ').replace('　', ' ');
		} catch (Exception e){
		        e.printStackTrace();
		}
			System.out.println("URL:\n" + page.url());
			System.out.println("title:\n" + title);
			System.out.println("time:\n" + time);
			System.out.println("para:\n" + paras);
			//防止字符串长度超过数据库字段最大长度
			if(paras.length()<255){
			paras = paras.substring(0, para.length());
			}else{
				paras = paras.substring(0,255);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
				try {
					//判断抓到的date是否为空
					if(!time.equals("")){
					date = sdf.parse(time);
					Timestamp sqldate = new Timestamp(date.getTime());
					//只有大于最大时间才插入数据库
					if(sqldate.after(urlDao.queryMaxTime("mobile.zol.com.cn"))){
					urlDao.insertURL(page.url(), title, paras, date);
					}
					}
					System.out.println("date:\n" + date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) throws Exception {
		ZolCrawler crawler = new ZolCrawler("crawl", true);
		crawler.setThreads(100);
		crawler.setTopN(70);
		// crawler.setResumable(true);
		/* start crawl with depth of 4 */
		crawler.start(2);
	}

}
