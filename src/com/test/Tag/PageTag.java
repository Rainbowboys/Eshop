package com.test.Tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.test.Model.PagingBean;

	public class PageTag extends SimpleTagSupport {
		private PagingBean pagingBean;
		private HttpServletRequest request;

		@Override
		public void doTag() throws JspException, IOException {
			// TODO Auto-generated method stub
			StringBuffer sb = new StringBuffer();
			if (pagingBean != null) {
				sb.append("<nav><ul class='pagination'>");
				// 处理上一页和首页
				if (pagingBean.getCurrentPage() <= 0) {
					sb.append("<li class='disabled'><a>首页</a></li>");
					sb.append("<li class='disabled'><a href='#'>上一页</a></li>");
				} else {
					sb.append("<li><a href='")
							.append(pagingBean.getPrefixUrl())
							.append("' aria-label='Previous'><span aria-hidden='true'>首页</span></a></li>");
					sb.append("<li><a href='").append(pagingBean.getPrefixUrl())
							.append(pagingBean.isAnd() ? "?" : "")
							.append("currentPage=")
							.append(pagingBean.getCurrentPage() - 1)
							.append("'>上一页</a></li>");
				}
				// 处理下一页和尾页
				if (pagingBean.getCurrentPage() >= (pagingBean.getPageCount() - 1)) {
					sb.append("<li class='disabled'><a>下一页</a></li>");
					sb.append("<li class='disabled'><a>尾页</a></li>");
				} else {
					sb.append("<li><a href='").append(pagingBean.getPrefixUrl())
							.append(pagingBean.isAnd() ? "?" : "")
							.append("currentPage=")
							.append(pagingBean.getCurrentPage() + 1)
							.append("'>下一页</a></li>");
					sb.append("<li><a href='")
							.append(pagingBean.getPrefixUrl())
							.append(pagingBean.isAnd() ? "?" : "")
							.append("currentPage=")
							.append(pagingBean.getPageCount() - 1)
							.append("' aria-label='Previous'><span aria-hidden='true'>尾页</span></a></li>");
				}
				sb.append("<li><a href='#'>");
				sb.append("<span>");
				sb.append(pagingBean.getCurrentPage() + 1);
				sb.append("/");
				sb.append(pagingBean.getPageCount());
				sb.append("</span>");
				sb.append("</a>");
				sb.append("</li>");
				sb.append("</ul></nav>");
			}
			getJspContext().getOut().write(sb.toString());
		}

		public PagingBean getPagingBean() {
			return pagingBean;
		}

		public void setPagingBean(PagingBean pagingBean) {
			this.pagingBean = pagingBean;
		}

		public HttpServletRequest getRequest() {
			return request;
		}

		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}
	}


