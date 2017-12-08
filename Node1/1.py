from eve import Eve
from flask_bootstrap import Bootstrap
import flask
from eve_docs import eve_docs

app = Eve()

accept = ["application/json", "application/xml", "*/*", "*/*;q=0.8", "application/xml;q=0.9"]

app.name = 'TaskAPI'
app.blueprints = {'name': 'TaskAPI'}

@app.after_request
def after_request(response):
    request_accept = flask.request.headers["Accept"].split(",")
    if not any(a in accept for a in request_accept):
        response = flask.make_response('{"_error": {"code": 406, "message": "NOT ACCEPTABLE"},"_status": "ERR"}', 406)
        response.content_type = "application/json"
    return response

if __name__ == '__main__':
    Bootstrap(app)
    app.register_blueprint(eve_docs, url_prefix='/docs')
    app.run(host='46.101.152.227')
